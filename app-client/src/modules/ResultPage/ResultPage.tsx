import React from 'react';
import { RouteProps } from 'react-router';
import { Spinner } from 'reactstrap';
import PieChart from 'components/Chart/Chart';
import DoughnutChart from 'components/Chart/DoughnutChart';
import { connect } from "react-redux";
import { ComparisonState } from 'store/DetailsResult/store';
import { AppState } from "store/index";
import { fetchDetailsResult } from 'api/client';
import * as P from './parts';
import * as PR from '../ResultListPage/parts';
import { Dispatch, bindActionCreators } from 'redux';
import { RouteComponentProps } from 'react-router-dom';
import { WebSocketState } from 'store/websockets/store';
import { StoreState } from 'store/SingleArticle/store';
import ProgresBar from 'components/StepProgress/StepProgress';

interface MatchParams {
    comparisonId1: string;
    comparisonId2: string;
}

interface MatchProps extends RouteComponentProps<MatchParams> {}

interface ResultProps extends RouteProps  {
    singleArticle: StoreState;
    comparison: ComparisonState;
    comparisonList: WebSocketState;
    getComparison: (url: string) => void;
}

interface ResultState {
    error: boolean,
    isLoading: boolean,
    matchedElement: number;
}

class Result extends React.Component<ResultProps & MatchProps, ResultState> {
    state = {
          error: false,
          isLoading: true,
          matchedElement: -1,
    }

    getResultList(id1: string, id2: string, metric: string) {
        const { getComparison } = this.props;
        getComparison(`/core/compare/two?articleId1=${id1}&articleId2=${id2}&metric=${metric}`);
        
    }

    getUrlParameter(name: string) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        const regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

        const results = regex.exec(this.props.location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    };

    componentDidMount() {
        const id1 = this.props.match.params.comparisonId1;
        const id2 = this.props.match.params.comparisonId2;
        const metric  = !!this.props.singleArticle.singleArticle.metrics
                        ? this.props.singleArticle.singleArticle.metrics[0]
                        : 'CosineSimilarity';

        this.getResultList(id1, id2, metric);
     }

    waitForResponse() {
        setTimeout(() => {
            this.setState({
                isLoading: false
            })
        }, 2000);
    }
     
    render(){
        const spinner = <Spinner className="spinner"></Spinner>;
        
        const isFetching = this.props.comparison.pending;

        const similarity = this.props.comparison.comparison ? this.props.comparison.comparison.similarityPercentage : 0;
        const plagiarism = this.props.comparison.comparison ? this.props.comparison.comparison.percentage : 0;

        const text1 = this.props.comparison.comparison ? this.props.comparison.comparison.firstArticleContent : '';
        const text2 = this.props.comparison.comparison ? this.props.comparison.comparison.secondArticleContent : '';

        const list = this.props.comparison.comparison ? this.props.comparison.comparison.suspiciousWords : [];
        const { matchedElement } = this.state;

        const percentage = this.getUrlParameter('percentage');
        // console.log(percentage)

        return(
            <section>
                <ProgresBar step={3} status={'pass'}/>
                <div className="container">
                    {   
                        isFetching
                        ? 
                        <PR.SpinnerWrapper>
                            {spinner}
                            <p>Text comparison in progress</p>
                        </PR.SpinnerWrapper>
                        : 
                        <>
                            {
                                <div className="row">
                                    <div className="col-md-12 chart-wrapper">
                                        {
                                            <DoughnutChart 
                                                metric={this.props.singleArticle.singleArticle.metrics[0]}
                                                plagiarism={percentage}
                                            />
                                        }
                                    </div>
                                    <div className="col-6 article__item">
                                        <h3>Your text</h3>
                                        {
                                            similarity + plagiarism === 0
                                            ? <>{text1}</>
                                            : list.sort((item1, item2) => (item1.firstArticleStart > item2.firstArticleStart) ? 1 : -1)
                                                .map((item, index) => {
                                                return <span key={index}>
                                                    {   
                                                        !!index 
                                                        ? text1.substring(list[index-1].firstArticleEnd, list[index].firstArticleStart) 
                                                        : text1.substring(0, list[index].firstArticleStart)
                                                        
                                                    }
                                                    <P.SuspiciousFragment  color={'red'}>
                                                        <a href={`#${item.firstArticleEnd}_link`} onClick={() => this.setState({matchedElement: item.secondArticleStart})}>
                                                            {text1.substring(item.firstArticleStart, item.firstArticleEnd)}
                                                        </a>
                                                    </P.SuspiciousFragment>
                                                </span>
                                            })
                                        }
                                        {!!list.length ? text1.substring(list[list.length-1].firstArticleEnd, text1.length) : null}
                                    </div>
                                    <div className="col-6 article__item">
                                        <h3>Text to compare</h3>
                                        {
                                            similarity + plagiarism === 0
                                            ? <>{text2}</>
                                            : list.sort((item1, item2) => (item1.secondArticleStart > item2.secondArticleStart) ? 1 : -1)
                                                .map((item, index) => {
                                                return <span key={index}>
                                                    {   
                                                        !!index 
                                                        ? text2.substring(list[index-1].secondArticleEnd, list[index].secondArticleStart) 
                                                        : text2.substring(0, list[index].secondArticleStart)
                                                    }
                                                    <P.SuspiciousFragment id={`${item.firstArticleEnd}_link`} color={'red'}>
                                                        {   matchedElement === item.secondArticleStart
                                                            ? <mark>{text2.substring(item.secondArticleStart, item.secondArticleEnd)}</mark>
                                                            : text2.substring(item.secondArticleStart, item.secondArticleEnd)
                                                        }
                                                    </P.SuspiciousFragment>        
                                                </span>
                                            })
                                        }
                                        {!!list.length ? text2.substring(list[list.length-1].secondArticleEnd, text2.length) : null}
                                    </div>  
                                </div>
                            }
                            <div className="row">
                                <div className="col-md-12 chart-wrapper">
                                    {                                    
                                        <PieChart 
                                            similarity={similarity} 
                                            plagiarism={plagiarism} 
                                        />
                                    }
                                </div>
                            </div>
                        </>
                        }
                </div>                
            </section>
        );
    }
    
}

const mapStateToProps = (state: AppState) => ({
    singleArticle: state.singleArticle,
    comparison: state.comparison,
});

const mapDispatchToProps = (dispatch: Dispatch) => bindActionCreators({
    getComparison: fetchDetailsResult,
}, dispatch)

export default connect(
    mapStateToProps,
    mapDispatchToProps,
)(Result);

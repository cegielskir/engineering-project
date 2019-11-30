import React from 'react';
import { RouteProps } from 'react-router';
import { RouteComponentProps } from 'react-router-dom';
import { Spinner } from 'reactstrap';
import { Link } from 'react-router-dom';
import { AppState } from "store/index";
import { connect } from 'react-redux';
import * as P from './parts';
import { WebSocketState } from 'store/websockets/store';
import { StoreState } from 'store/SingleArticle/store';
import { client } from '../WSResultListPage/WSResultList';
import ProgresBar from 'components/StepProgress/StepProgress';
import DropdownList from 'components/DropdownList/DropdownList';
import searchSVG  from 'assets/img/search.svg';
import { fetchSearchList, clearStore, clearWSStore } from 'store/websockets/socket-client';
import { Dispatch, bindActionCreators } from 'redux';
import { Article } from 'store/ws_search_result/actions';
import { SearchState } from 'store/ws_search_result/store';
import { ComparisonList } from 'store/websockets/actions';

interface ResultListState {
    result: ComparisonList[],
    error: string,
    isLoading: boolean;
    sorted: string;
    metric: string;
    searchedPhrase: string;
    searchEngine: boolean;
    ids: string[];
}

interface MatchParams {
    pageNumber: string;
}

interface MatchProps extends RouteComponentProps<MatchParams> {}

interface ResultListProps extends RouteProps {
    singleArticle: StoreState;
    comparisonList: WebSocketState;
    searchList: SearchState;
    getResult: (article: Article) => void;
    clearResult: () => void;
    clearComparisons: () => void;
}

class ResultList extends React.Component<ResultListProps & MatchProps, ResultListState> {
    state = {
        result: [],
        error: '',
        isLoading: false,
        sorted: '',
        metric: this.props.singleArticle.singleArticle.metrics[0],
        searchedPhrase: '',
        searchEngine: false,
        ids: [''], 
    }
 
    componentDidMount() {
        this.setState({ isLoading: true }); 

        this.subscribeChannel();

        setTimeout(
            () => this.setState({
                isLoading: false
            }), 50
        );
     }

     subscribeChannel = () => {
        const { getResult } = this.props;
        try {
            client.subscribe('/article/find',
                (message) => {
                    if (message.body) {
                        let response = JSON.parse(message.body);
                        if('key' in response) console.log('[SUCCESS]', response);
                        else getResult(response);
                        this.setState({
                            searchEngine: true,
                        })
                    }
                }
            );
          } catch(e) {
            console.error(e);
          }
     }

     sendIds = () => {
        const { id, metrics, threshold } = this.props.singleArticle.singleArticle;
  
        try {
          console.log('send', id);
          client.send('/app/compare/' + id +
                      '/' + threshold +
                      '/' + this.state.ids +
                      '/' + metrics,
                      {}, '');
        } catch (e) {
          console.error(e);
        }
      }
  

      

     returnArticlesList() { 
        const pageNr = Number(this.props.match.params.pageNumber);
        const { id } = this.props.singleArticle.singleArticle;
        const { comparisons } = this.props.comparisonList;
        const { sorted, metric } = this.state;

        if(comparisons !== undefined && !!comparisons.length) { 
            let comparisonList = comparisons.filter(item => item.metric === metric);
            sorted === 'Ascending' && comparisonList.sort((item1, item2) => (item1.percentage < item2.percentage) ? 1 : -1);
            sorted === 'Descending' && comparisonList.sort((item1, item2) => (item1.percentage > item2.percentage) ? 1 : -1);

            
            return (
                <div className="row">
                    <div className="col-3">
                        <h4>Result list:</h4>
                        {comparisonList
                            .slice((pageNr-1)*10, pageNr*10)
                            .map((item: ComparisonList, index) => {
                            return <p key={index}><a href={`#${item.articleIDs.filter(articleId => articleId != id)}`}>{`${item.secondArticleTitle.substring(0, 20)}`}...</a>{`: ${item.percentage}%`}</p>;
                            })
                        }
                    </div>
                    <div className="col-9">
                    {
                        comparisonList
                            .slice((pageNr-1)*10, pageNr*10)
                            .map((item: ComparisonList, index) => {                    
                            return (
                                <P.ResultItem key={index}>
                                    <div id={`${item.articleIDs.filter(articleId => articleId != id)}`} className="row">
                                        <div className="col-9">
                                            <h3>{item.secondArticleTitle}</h3>
                                            <h5>{item.secondArticleDescription}</h5>
                                            <p>{item.secondArticleShortContent}...</p>
                                        </div>
                            
                                        <div className="col-3">
                                            <P.CenterWrapper>
                                                <P.Percentage>{item.percentage}%</P.Percentage>   
                                                <Link to={`/result/${id}/${item.articleIDs.filter(articleId => articleId != id)}?percentage=${item.percentage}`} >
                                                    <P.CheckResult>
                                                        <P.Button>
                                                            Check
                                                        </P.Button>
                                                    </P.CheckResult>
                                                </Link>
                                            </P.CenterWrapper>
 
                                        </div>
                                    </div>
                                </P.ResultItem>      
                            )
                        })
                        
                    }
                    </div>
                </div>
            );
        }
        return null;
     }

     addIds = (id: string) => {
         let newIds = this.state.ids;
         newIds.push(id);
         this.setState({
            ids: newIds.filter(m => m !== ''),
         })
     }

     returnSearchList = () => {
        const searchList  = this.props.searchList.results;

        return (
            <div className="row">
                <div className="col-3">
                    <h4>Selected articles:</h4>
                    <ul>
                        {this.state.ids.filter(m => m !== '').map((idk, index) => {
                            return <li key={index}>{idk}</li>;
                        })}
                    </ul>
                    <button onClick={this.backToPrevPage}>
                        Check
                    </button>
                </div>
                <div className="col-9">
                {
                    searchList.map((item, index) => {
                        return (
                            <P.Article key={index}>
                                <p>Autor: {item.author}</p>
                                <h3>{item.title}</h3>
                                <h5>{item.description}</h5>
                                <p>{item.content.substring(0, 500)}...</p>
                                <button onClick={() => this.addIds(item.id)}>Add</button>
                            </P.Article>
                        );
                    })
                }
                </div>
            </div>
            );
        }

        
     
    
     generateLinks = (length: number) => (
        <>
            {
                !!length 
                ? <Link to="/result-list/1"><P.PageNr>1</P.PageNr></Link>
                : null
            }
            {
                length >= 1
                ? <Link to="/result-list/2"><P.PageNr>2</P.PageNr></Link>
                : null
            }
            {
                length >= 2
                ?   <>
                        <Link to="/result-list/3"><P.PageNr>3</P.PageNr></Link>
                        <P.PageNr>...</P.PageNr>
                        <Link to={`/result-list/${Number(this.props.match.params.pageNumber)+1}`}><P.PageNr>>></P.PageNr></Link>
                    </>
                : null
            }
        </>
     );

    onMetricChange = (item: string) => {
        this.setState({
            metric: item,
        })
    }

    onSortChange = (item: string) => {
        this.setState({
            sorted: item,
        })
    }

    handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            searchedPhrase: e.target.value,
        });
    };

    renderActions = () => (
        <P.ButtonWrapper>
            <DropdownList title={`Filter by: ${!!this.state.metric ? this.state.metric : ''}`} items={this.props.singleArticle.singleArticle.metrics} chooseOption={this.onMetricChange.bind(this)} />

            <P.InputWrapper>
                <P.Input 
                    type="text" 
                    value={this.state.searchedPhrase} 
                    onChange={this.handleInputChange} 
                />
                <P.SearchButton onClick={this.onSendClick}>
                    <P.SearchIcon src={searchSVG} />
                </P.SearchButton>
            </P.InputWrapper>

            <DropdownList title={`Sort by: ${this.state.sorted}`} items={['Ascending', 'Descending']} chooseOption={this.onSortChange.bind(this)} />
        </P.ButtonWrapper>
    );

    onForceClose = () => {
        try {
            console.log('send dispose');
            client.send('/app/compare/dispose');
          } catch (e) {
            console.error(e);
          }
    }

    onSendClick = () => {
        try {
            console.log('send')
            client.send('/app/article/find/content/' + this.state.searchedPhrase,
                        {}, '');
          } catch (e) {
            console.error(e);
        }
    }

    backToPrevPage = () => {
        const { clearComparisons, clearResult } = this.props;
        clearComparisons();
        clearResult();
        this.sendIds();
        this.setState({
            searchEngine: false,
            ids: [],
        })
    }

    render(){
        const spinner = <Spinner className="spinner"></Spinner>;
        const { comparisons } = this.props.comparisonList;
        const { metric, searchEngine } = this.state;

        return(
            <section>
                <div className="container">
                    <ProgresBar step={2} status={'pass'}/>
                    { 
                        this.state.isLoading
                        ? 
                            <P.SpinnerWrapper>
                                {spinner}
                                <p>Text comparison in progress</p>
                            </P.SpinnerWrapper>
                        :  
                        <>
                            {this.renderActions()}
                                { 
                                    searchEngine
                                    ? this.returnSearchList()
                                    :   
                                    <>
                                        {this.returnArticlesList()}
                                        <P.Pagination>
                                            {this.generateLinks(comparisons.filter(c => c.metric === metric).length/10)}
                                        </P.Pagination>
                                    </> 
                                }
                        </>

                    }
                </div>                
            </section>
        );
    }
    
}

const mapStateToProps = (state: AppState) => ({
    singleArticle: state.singleArticle,
    comparisonList: state.ws,
    searchList: state.sa
});

const mapDispatchToProps = (dispatch: Dispatch) => bindActionCreators({
    getResult: fetchSearchList,
    clearResult: clearStore,
    clearComparisons: clearWSStore,
}, dispatch)

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(ResultList);
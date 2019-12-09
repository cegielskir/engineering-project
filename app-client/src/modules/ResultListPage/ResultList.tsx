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
import { TwoArticlesState } from 'store/TwoArticles/store';

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
    twoArticles: TwoArticlesState;
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
                        //console.log(response);
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
  

      sort = (item1: ComparisonList, item2: ComparisonList, type: string) => {
            const { metric } = this.state

            const v1 = Object.entries(item1.comparisonMap).filter(entry => entry[0] === metric)[0];
            const v2 = Object.entries(item2.comparisonMap).filter(entry => entry[0] === metric)[0];

            return type === 'ASC' 
                        ? (v1 < v2 ? 1 : -1)
                        : (v1 > v2 ? 1 : -1);
      }

     returnArticlesList() { 
        const pageNr = Number(this.props.match.params.pageNumber);
        const id = !!this.props.twoArticles.twoArticles.article1.id ? this.props.twoArticles.twoArticles.article1.id : this.props.singleArticle.singleArticle.id;
        const { comparisons } = this.props.comparisonList;
        const { sorted, metric } = this.state;

        if(comparisons !== undefined && !!comparisons.length) { 
            sorted === 'Ascending' && comparisons.sort((item1, item2) => (this.sort(item1, item2, 'ASC')));
            sorted === 'Descending' && comparisons.sort((item1, item2) => (this.sort(item1, item2, 'DSC')));

            return (
                <div className="row">
                    <div className="col-3">
                        <h4>Result list:</h4>
                        {comparisons
                            .slice((pageNr-1)*10, pageNr*10)
                            .map((item: ComparisonList, index) => {
                            return <div key={index}>
                                    <a href={`#${item.secondArticleID}`}>{`${item.secondArticleTitle.substring(0, 20)}`}...</a>
                                    <ul>
                                    {
                                        Object.entries(item.comparisonMap).map((entry) => {
                                            return <li key={item.secondArticleID + entry[0]}>{`${entry[0]}: ${entry[1]}%`}</li>;
                                        })   
                                    }</ul>
                                    </div>;
                            })
                        }
                    </div>
                    <div className="col-9">
                    {
                        comparisons
                            .slice((pageNr-1)*10, pageNr*10)
                            .map((item: ComparisonList, index) => {                    
                            return (
                                <P.ResultItem key={index}>
                                    <div id={`${item.secondArticleID}`} className="row">
                                        <div className="col-9">
                                            <h3>{item.secondArticleTitle}</h3>
                                            <h5>{item.secondArticleDescription}</h5>
                                            <p>{item.secondArticleShortContent}...</p>
                                        </div>
                            
                                        <div className="col-3">
                                            <P.CenterWrapper>
                                                <Link to={`/result/${id}/${item.secondArticleID}?percentage=${Object.values(item.comparisonMap)[0]}`} >
                                                    <P.CheckResult>
                                                        <P.Button>
                                                            Check details
                                                        </P.Button>
                                                    </P.CheckResult>
                                                </Link>
                                            </P.CenterWrapper>
 
                                        </div>
                                        <div className="col-12">
                                            <table>
                                                <tbody>
                                                    <tr>
                                                    {
                                                        Object.entries(item.comparisonMap).map((entry) => {
                                                            return <th key={item.secondArticleID + entry[0]}>{`${entry[0]}`}</th>;
                                                        })                                         
                                                    }
                                                    </tr>
                                                    <tr>
                                                    {
                                                        Object.entries(item.comparisonMap).map((entry) => {
                                                            return <td key={item.secondArticleID + entry[0]}>{`${entry[1]}`}</td>;
                                                        })                                         
                                                    }
                                                    </tr>
                                                </tbody>
                                            </table>

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
                                            {/* {this.generateLinks(comparisons.filter(c => c.metric === metric).length/10)} */}
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
    twoArticles: state.twoArticles,
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
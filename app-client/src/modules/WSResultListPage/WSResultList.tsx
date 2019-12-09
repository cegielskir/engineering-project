import React from 'react';
import { Redirect } from 'react-router-dom';
import { Spinner } from 'reactstrap';
import { connect } from 'react-redux';
import { WebSocketState } from 'store/websockets/store';
import { AppState } from "store/index";
import { Dispatch, bindActionCreators } from 'redux';
import { ComparisonList } from 'store/websockets/actions';
import { StoreState } from 'store/SingleArticle/store';
import { TwoArticlesState } from 'store/TwoArticles/store';
import * as PR from '../ResultListPage/parts';
import { fetchComparisonList } from 'store/websockets/socket-client';
import { Stomp } from "@stomp/stompjs";
import SockJS from 'sockjs-client';
import ProgresBar from 'components/StepProgress/StepProgress';

const SOCKET_URL = 'http://localhost:8060/text-comparator';
const socket = new SockJS(SOCKET_URL);
export const client = Stomp.over(socket);
client.debug = () => {
};
client.connect({}, () => {
  console.log('connect')
}
);

interface SocketState {
  readyToRedirect: boolean;
}

interface SocketProps {
  singleArticle: StoreState;
  twoArticles: TwoArticlesState;
  comparison: WebSocketState;
  getComparison: (comparison: ComparisonList) => void;
}

class SocketsPage extends React.Component<SocketProps, SocketState> {
  state = {
    readyToRedirect: false,
  }

    componentDidMount() {
      this.subscribeSocket();

      this.send();
    }

    subscribeSocket() {
      const { getComparison } = this.props;
      try {
        client.subscribe('/article/comparison',
          (message) => {
            // console.log('subscribe');
              if (message.body) {
                  let response = JSON.parse(message.body);
                  //console.log(response);
                  if('key' in response) console.log('[SUCCESS]', response);
                  else getComparison(response);
                  this.setState({
                    readyToRedirect: true,
                  })
              }
          }
        );
      } catch(e) {
        console.error(e);
      }
    }

    send() {
      const { threshold } = this.props.singleArticle.singleArticle;
      const id = !!this.props.singleArticle.singleArticle.id ? this.props.singleArticle.singleArticle.id : this.props.twoArticles.twoArticles.article1.id;
      const mockList = !!this.props.twoArticles.twoArticles.article1.id ? [this.props.twoArticles.twoArticles.article1.id, this.props.twoArticles.twoArticles.article2.id] : [id];
      const metrics = !!this.props.twoArticles.twoArticles.article1.id ? this.props.twoArticles.twoArticles.metrics : this.props.singleArticle.singleArticle.metrics;
      try {
        console.log('/app/compare/' + id +
        '/' + threshold +
        '/' + mockList +
        '/' + metrics);
        console.log('send', id);
        client.send('/app/compare/' + id +
                    '/' + threshold +
                    '/' + mockList +
                    '/' + metrics,
                    {}, '');
      } catch (e) {
        console.error(e);
      }
    }

    render() {

      const { readyToRedirect } = this.state;
      const spinner = <Spinner className="spinner"></Spinner>;

      return (
        <>
          <ProgresBar step={2} status={'active'}/>
          {
            readyToRedirect 
            ? <Redirect to="/result-list/1"/>
            : <PR.SpinnerWrapper>
                  {spinner}
                  <p>Text comparison in progress</p>
              </PR.SpinnerWrapper>
          }
        </>
      )
    }

}

const mapStateToProps = (state: AppState) => ({
  twoArticles: state.twoArticles,
  singleArticle: state.singleArticle,
  ws: state.ws,
});

const mapDispatchToProps = (dispatch: Dispatch) => bindActionCreators({
  getComparison: fetchComparisonList,
}, dispatch)

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(SocketsPage);


import React from 'react';
import * as P from './parts';
import { client } from '../WSResultListPage/WSResultList'; 

interface FindArticleState {
    input: string;
};

interface FindArticleProps {

};

class FindArticle extends React.Component<FindArticleProps, FindArticleState> {
    state = {
        input: '',
    }

    componentDidMount() {
        this.subscribeSocket();
    }
  
    handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            input: e.target.value,
        });
    };

      subscribeSocket = () => {
        try {

            client.subscribe('/article/searchEngine',
                (message) => {
                    console.log('subscribe');
                    if (message.body) {
                        let response = JSON.parse(message.body);
                        console.log(response);
                }
            })
 
        } catch(e) {
          console.error(e);
        }
      }
  
      send() {
        const { input } = this.state; 
        try {
          console.log('send');
          client.send('/app/find/'+input,
                      {}, '');
        } catch (e) {
          console.error(e);
        }
      }

      onFindClick = () => {
          this.send();
      }

    render() {
        const { input } = this.state;

        return (
            <>
                <P.Input 
                    type="text" 
                    value={input} 
                    onChange={this.handleChange} 
                />
                <button onClick={this.onFindClick}>Search</button>
            </>
        );
    }
}

export default FindArticle;

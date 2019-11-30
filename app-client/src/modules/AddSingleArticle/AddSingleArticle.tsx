import React from 'react';
import { postData } from 'api/client';
import * as P from './parts';
import * as PH from '../HomePage/parts';
import { Button } from 'assets/global-styles';
import TopbarMessage from 'components/TopbarMessage/TopbarMessage';
import { addArticle  } from 'store/SingleArticle/actions';
import { connect } from "react-redux";
import { AppState } from "store";

interface SingleArticleProps {
    addArticle: typeof addArticle;
}

interface SingleArticleState {
    articleId: string;
    textareaContent: string;
    error: boolean;
    errorMsg: string;
    title: string;
    author: string;
    description: string;
};

class SingleArticle extends React.Component<SingleArticleProps, SingleArticleState> {
    state = {
        articleId: '',
        textareaContent: '',
        error: false,
        errorMsg: '',
        title: '',
        author: '',
        description: '',
    }
    
    async postArticle() {
        const { textareaContent, title, author, description } = this.state;
        if(!!textareaContent) {
            postData("/core/article", 
                {
                    title: title,
                    author: author,
                    description: description,
                    content: textareaContent,
                    url: window.location.href,
                    date: new Date().toJSON().slice(0,10),
                    downloadTime: new Date().toJSON().slice(11,19),
                    numberOfViews: 0,
                }
            )
            .then(res => {
                this.setState({
                    articleId: res._id,
                });
            })
            .catch(err => {
                this.setState({
                    error: true,
                })
            });
        } else {
            this.setState({
                error: true,
            })
        }
    }

    handleChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        this.setState({
            textareaContent: e.target.value,
        });
    };

    handleTitleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            title: e.target.value,
        });
    };

    handleAuthorChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            author: e.target.value,
        });
    };

    handleDescriptionChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        this.setState({
            description: e.target.value,
        });
    };

    openAdvancedSettings = () => {

    };

    render() {
        const { articleId, textareaContent, error, description, title, author } = this.state; 
        console.log(this.state);
        return(
            <PH.PageContainer>
                {
                    error && 
                    <TopbarMessage 
                        open={error}
                        label={'Unfortunately some error ocured during adding your article.'}
                        color={'#cc0000'}
                    />
                }
                {
                    articleId && 
                    <TopbarMessage 
                        open={true}
                        label={'Article was added successfully.'}
                        color={'#00c851'}
                    />
                }
                <PH.FormWrapper>
                    <P.InputWrapper>
                        <div>
                            <P.Label>Author</P.Label>
                            <P.Input 
                                type="text" 
                                value={title} 
                                onChange={this.handleTitleChange} 
                            />
                        </div>
                        <div>
                            <P.Label>Title</P.Label>
                            <P.Input 
                                type="text" 
                                value={author} 
                                onChange={this.handleAuthorChange} 
                            />
                        </div>
                    </P.InputWrapper>
        
                        <P.Label>Description</P.Label>
                        <PH.TextArea 
                            className="form-control text-checker" 
                            id="exampleFormControlTextarea2" 
                            rows={3} 
                            value={description} 
                            onChange={this.handleDescriptionChange}
                        />
       
                        <P.Label>Content</P.Label>
                        <PH.TextArea 
                            className="form-control text-checker" 
                            id="exampleFormControlTextarea1" 
                            rows={8} 
                            value={textareaContent} 
                            onChange={this.handleChange}
                        />
       

                    <PH.ButtonWrapper>
                        <Button 
                            className="btn btn-outline-secondary btn-checker"
                            onClick={() => this.postArticle()} 
                            disabled={false}
                        >
                            Check content
                        </Button>
                    </PH.ButtonWrapper>

                    <h3>Quick Text Checker with smart Citation Marker</h3>
                </PH.FormWrapper>
                <PH.WavesSpace>
                    <PH.WavesSpaceContent>
                        <h3>This tool is created as a part of engineering project: <br/><i>'Components for detection of similar 
                        text content dedicated to Polish language'</i>.</h3>
                    </PH.WavesSpaceContent>
                </PH.WavesSpace>  
            </PH.PageContainer>
        );
    }
}

const mapStateToProps = (state: AppState) => ({
    singleArticle: state.singleArticle
});
  
export default connect(
    mapStateToProps,
    { addArticle }
)(SingleArticle);

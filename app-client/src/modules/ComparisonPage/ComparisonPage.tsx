import React from 'react';
import { Redirect } from 'react-router-dom';
import { postData } from 'api/client';
import { addTwoArticles } from 'store/TwoArticles/actions';
import { connect } from "react-redux";
import { AppState } from "store";
import * as P from './parts';
import { Button } from 'assets/global-styles';
import { getMethods } from 'api/client';
import TopbarMessage from 'components/TopbarMessage/TopbarMessage';
import ProgresBar from 'components/StepProgress/StepProgress';

interface AvailableMethods {
    name: string;
    metricType: string;
    detailedMethodAvailable: boolean;
}

interface ComparisonProps {
    addTwoArticles: typeof addTwoArticles;
}

interface ComparisonState {
    articleId1: string;
    textareaContent1: string;
    articleId2: string;
    textareaContent2: string;
    error: boolean;
    metrics: string[];
    metricType: string;
    isOpen: boolean;
    errorMsg: string;
    availableMethods: AvailableMethods[];
    isConnectionReady: boolean;
};

export class Comparison extends React.Component<ComparisonProps, ComparisonState> {
    state = {
        articleId1: '',
        textareaContent1: '',
        articleId2: '',
        textareaContent2: '',
        metrics: [''],
        metricType: 'EditBased',
        error: false,
        isOpen: false,
        errorMsg: '',
        availableMethods: [],
        isConnectionReady: false,
    }
    
    componentDidMount() {
        this.fetchMetrics();
    }

    fetchMetrics = () => {
        getMethods()
            .then(res => {
                this.setState({
                    availableMethods: res,
                })
            }).catch(err => {
                this.setState({
                    errorMsg: err,
                })
            });
    }

    async postArticle(text: string, num: number) {

        if(!!text) {
            postData("/core/article", 
                {
                    title: 'Single text provided',
                    author: 'guest',
                    description: 'Single text to compare',
                    content: text,
                }
            )
            .then(res => {
                this.setState({
                    articleId1: res.id,
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

    renderAvailableMethods(availableMethods: AvailableMethods[]) {
        const { metrics, metricType  } = this.state;
        if(!Array.isArray(availableMethods) || metrics.length === 0) {
            return <></>;
        }

        const availableMetricsTypes = new Map<string, string>();

        availableMethods.forEach(availableMethod => {
            availableMetricsTypes.set(availableMethod.metricType, availableMethod.name);
        })
    
        const metricsTypes = Array.from(availableMetricsTypes.keys());
        
        return (
            <>
                <h5>Choose metric type:</h5>
                <P.RadioWrapper> 
                {
                    metricsTypes.map((availableMetric, index) => {
                        return (
                            <P.Radio key={index} 
                                    onClick={() => this.handleMetricTypeChange(availableMetric)} 
                                    isSelected={ metricType === availableMetric} 
                            >
                                {availableMetric.replace(/([A-Z])/g, ' $1').trim()}
                            </P.Radio>
                        )
                    })
                }
                </P.RadioWrapper>

                <h5>Choose compare algorithm</h5>
                <P.RadioWrapper>
                    {
                        availableMethods
                            .filter(availableMethod => availableMethod.metricType === metricType)
                            .map((availableMethod, index) => {
                                return (
                                    <P.Radio 
                                        key={index}
                                        onClick={() => this.handleMetricChange(availableMethod.name)} 
                                        isSelected={ metrics.includes(availableMethod.name) } 
                                    >
                                        {availableMethod.name.replace(/([A-Z])/g, ' $1').trim()}
                                    </P.Radio>
                                )
                            })
                    }
                </P.RadioWrapper>  
            </>
        );
    }


    handleChange1 = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        this.setState({
            textareaContent1: e.target.value,
        });
    };

    handleChange2 = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        this.setState({
            textareaContent2: e.target.value,
        });
    };

    handleMetricTypeChange = (metricType: string) => {
        this.setState({
            metricType: metricType,
        })
    };

    handleMetricChange = (metric: string) => {
        let newMetrics = this.state.metrics; 
        newMetrics.includes(metric) ? newMetrics = newMetrics.filter(m => m !== metric) : newMetrics.push(metric);

        this.setState({
            metrics: newMetrics.filter(m => m !== ''),
        })
    };

    openAdvancedSettings = () => {
        this.setState((prevState) => ({
            isOpen: !prevState.isOpen,
        }));
    };

    render() {
        const { articleId1, textareaContent1, articleId2, textareaContent2, error, metrics, isOpen, availableMethods } = this.state;
        // console.log(this.state);
        if (!!articleId1 && !!articleId2) {
            this.props.addTwoArticles({
                id: articleId1, 
                content: textareaContent1,
                metrics: metrics,
            }, {
                id: articleId2, 
                content: textareaContent2,
                metrics: metrics,
            });
            return <Redirect to='/socket-connection' />
        }

        return (
        <P.PageContainer>
        {
            error && 
            <TopbarMessage 
                open={error}
                label={'Unfortunately some error ocured during adding your article.'}
                color={'#cc0000'}
            />
        }
        <ProgresBar step={1} status={'pending'}/>
        <P.FormWrapper>
            <P.TextAreaWrapper>
                <P.TextArea 
                    className="form-control text-checker" 
                    id="exampleFormControlTextarea1" 
                    rows={8} 
                    value={textareaContent1} 
                    onChange={this.handleChange1}
                />
                <P.TextArea 
                    className="form-control text-checker" 
                    id="exampleFormControlTextarea2" 
                    rows={8} 
                    value={textareaContent2} 
                    onChange={this.handleChange2}
                />
            </P.TextAreaWrapper>

            <P.ButtonWrapper>

                <Button 
                    className="btn btn-outline-secondary btn-checker"
                    onClick={this.openAdvancedSettings} 
                    disabled={false}
                >
                    Advanced Settings
                </Button>
                <Button 
                    className="btn btn-outline-secondary btn-checker"
                    onClick={() => this.postArticle(textareaContent1, 1)} 
                    disabled={false}
                >
                    Check content
                </Button>

            </P.ButtonWrapper>
            <P.AdvancedSettings isOpen={isOpen}>

                {this.renderAvailableMethods(availableMethods)}

            </P.AdvancedSettings>
        </P.FormWrapper>
    </P.PageContainer>
        );
      }
}

const mapStateToProps = (state: AppState) => ({
    twoArticles: state.twoArticles,
});
  
export default connect(
    mapStateToProps,
    { addTwoArticles }
)(Comparison);

import React from 'react';
import { Redirect } from 'react-router-dom';
import item from 'assets/img/item1.png';
import { postData, getMethods } from 'api/client';
import * as P from './parts';
import { Button } from 'assets/global-styles';
import TopbarMessage from 'components/TopbarMessage/TopbarMessage';
import { addArticle  } from 'store/SingleArticle/actions';
import { connect } from "react-redux";
import { AppState } from "store";
import ProgresBar from 'components/StepProgress/StepProgress';

interface AvailableMethods {
    name: string;
    metricType: string;
    detailedMethodAvailable: boolean;
}

interface HomeProps {
    addArticle: typeof addArticle;
}

interface HomeState {
    articleId: string;
    textareaContent: string;
    error: boolean;
    metrics: string[];
    metricType: string;
    isOpen: boolean;
    threshold: number;
    errorMsg: string;
    availableMethods: AvailableMethods[];
    isConnectionReady: boolean;
};

class Home extends React.Component<HomeProps, HomeState> {
    state = {
        articleId: '',
        textareaContent: '',
        metrics: [''],
        metricType: 'EditBased',
        threshold: 0,
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

    async postArticle(text: string) {
        const { textareaContent } = this.state;
        if(!!textareaContent) {
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
                    articleId: res.id,
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


    handleChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        this.setState({
            textareaContent: e.target.value,
        });
    };

    handleThresholdChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({
            threshold: Number(e.target.value),
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

    render(){
        const { articleId, textareaContent, error, metrics, isOpen, threshold, availableMethods } = this.state;
        // console.log(this.state);
        if (!!articleId) {
            this.props.addArticle({
                id: articleId, 
                content: textareaContent,
                metrics: metrics,
                threshold: threshold,
            });
            return <Redirect to='/socket-connection' />
        }

        return(
            <>
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
                        <P.TextArea 
                            className="form-control text-checker" 
                            id="exampleFormControlTextarea1" 
                            rows={8} 
                            value={textareaContent} 
                            onChange={this.handleChange}
                        />
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
                                onClick={() => this.postArticle(textareaContent)} 
                                disabled={false}
                            >
                                Check content
                            </Button>

                        </P.ButtonWrapper>
                        <P.AdvancedSettings isOpen={isOpen}>
                            
                            <h5>Choose threshold</h5>
                            <P.Input 
                                type="text" 
                                value={this.state.threshold} 
                                onChange={this.handleThresholdChange} 
                            />

                            {this.renderAvailableMethods(availableMethods)}

                        </P.AdvancedSettings>
                        <h3>Quick Text Checker with smart Citation Marker</h3>
                    </P.FormWrapper>
                    <P.WavesSpace>
                        <P.WavesSpaceContent>
                            <h3>This tool is created as a part of engineering project: <br/><i>'Components for detection of similar 
                            text content dedicated to Polish language'</i>.</h3>
                        </P.WavesSpaceContent>
                    </P.WavesSpace>  
                </P.PageContainer>

            <section className="services">
                <div className="container">
                <h1>Services</h1>
                <p>List of all available services</p>
                <div className="row services__all">

                    <div className="col-4 services__image">
                        <img alt="item-1" src={ item } />
                    </div>
                    <div className="col-8 services__description">
                        <h3>Compare two documents</h3>
                        <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque purus tortor, sagittis non auctor quis, dapibus a arcu. Ut sit amet nunc a erat finibus bibendum vel in leo. Mauris facilisis ex at euismod efficitur. Aliquam vel neque nisi. Integer rhoncus nec sem sed finibus. Aenean id commodo magna. Duis a magna magna. Morbi at erat sit amet urna dignissim condimentum vitae a risus. Fusce maximus enim ac nunc porta porta. Maecenas vel odio nisl. Suspendisse consequat urna turpis, at congue ex sagittis quis.
                        </p>
                    </div>

                    <div className="col-8 services__description">
                        <h3>Text content checker</h3>
                        <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque purus tortor, sagittis non auctor quis, dapibus a arcu. Ut sit amet nunc a erat finibus bibendum vel in leo. Mauris facilisis ex at euismod efficitur. Aliquam vel neque nisi. Integer rhoncus nec sem sed finibus. Aenean id commodo magna. Duis a magna magna. Morbi at erat sit amet urna dignissim condimentum vitae a risus. Fusce maximus enim ac nunc porta porta. Maecenas vel odio nisl. Suspendisse consequat urna turpis, at congue ex sagittis quis.
                        </p>
                    </div>
                    <div className="col-4 services__image">
                        <img alt="item-1" src={ item } />
                    </div>

                    <div className="col-4 services__image">
                        <img alt="item-1" src={ item } />
                    </div>
                    <div className="col-8 services__description">
                        <h3>Text searcher</h3>
                        <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque purus tortor, sagittis non auctor quis, dapibus a arcu. Ut sit amet nunc a erat finibus bibendum vel in leo. Mauris facilisis ex at euismod efficitur. Aliquam vel neque nisi. Integer rhoncus nec sem sed finibus. Aenean id commodo magna. Duis a magna magna. Morbi at erat sit amet urna dignissim condimentum vitae a risus. Fusce maximus enim ac nunc porta porta. Maecenas vel odio nisl. Suspendisse consequat urna turpis, at congue ex sagittis quis.
                        </p>
                    </div>
                </div>
                </div>

            </section>
            </>
        );
    }
}

const mapStateToProps = (state: AppState) => ({
    singleArticle: state.singleArticle
});
  
export default connect(
    mapStateToProps,
    { addArticle }
)(Home);
  

import React from 'react';
import './StepProgress.css';

interface StepProgresProps {
    step: number;
    status: string;
}

const StepProgress: React.FC<StepProgresProps> = ({ step, status }) => {
    return (
        <div className="container">
        <table className="stepbar-progress" data-current-step={step} data-step-status={status}>
            <tbody>
                <tr>
                    <td className="step-item step-item-first" data-step="1">
                        <span className="step-body">
                            <span className="glyphicon"/>
                        </span>
                    </td>
                    <td className="step-item-progress">
                        <span className="progress-body">
                            <span className="body-fill"/>
                        </span>
                    </td>
                    <td className="step-item step-item-middle" data-step="2">
                        <span className="step-body">
                            <span className="glyphicon"/>
                        </span>
                    </td>
                    <td className="step-item-progress">
                        <span className="progress-body">
                            <span className="body-fill"/>
                        </span>
                    </td>
                    <td className="step-item step-item-last" data-step="3">
                        <span className="step-body">
                            <span className="glyphicon"/>
                        </span>
                    </td>
                </tr>
            </tbody>
        </table>
        <div className="row">
            <div className="col-4 step-label">{step === 1 ? <b>Provide text</b> : <p>Provide text</p>}</div>
            <div className="col-4 text-center step-label">{step === 2 ? <b>Fetch result list</b> : <p>Fetch result list</p>}</div>
            <div className="col-4 text-right step-label">{step === 3 ? <b>Get details result</b> : <p>Get details result</p>}</div>
        </div>
        </div>


    );
};

export default StepProgress;
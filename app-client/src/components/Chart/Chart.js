import React, { Component } from 'react';
import CanvasJSReact from 'assets/canvasjs.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;
 
class PieChart extends Component {
	render() {
		const options = {
			exportEnabled: true,
			animationEnabled: true,
			title: {
				text: "Text percentage similarity"
			},
			data: [{
				type: "pie",
				startAngle: 75,
				toolTipContent: "<b>{label}</b>: {y}%",
				showInLegend: "true",
				legendText: "{label}",
				indexLabelFontSize: 16,
				indexLabel: "{label} - {y}%",
				dataPoints: [
                    { y: this.props.similarity, label: "Paraphrased text" },
                    { y: this.props.plagiarism, label: "Simmilarity" },
                    { y: 100.0 - (this.props.similarity+this.props.plagiarism), label: "Unique" }
				]
			}]
		}
		
		return (
		    <>
			    <CanvasJSChart options={options} />
		    </>
		);
	}
}

export default PieChart;
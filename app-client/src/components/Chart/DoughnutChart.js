import React, { Component } from 'react';
import CanvasJSReact from 'assets/canvasjs.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class DoughnutChart extends Component {
	render() {
		const options = {
			animationEnabled: true,
			title: {
				text: "Comparison using " + this.props.metric + " metric."
			},
			subtitles: [{
				text: this.props.plagiarism + " Plagiarism",
				verticalAlign: "center",
				fontSize: 24,
				dockInsidePlotArea: true
			}],
			data: [{
				type: "doughnut",
				showInLegend: true,
				indexLabel: "{name}: {y}",
				yValueFormatString: "#,###'%'",
				dataPoints: [
					{ name: "Simmilarity", y: this.props.plagiarism },
					{ name: "Unique", y: 100 - this.props.plagiarism },
				]
			}]
		}
		return (
            <div>
                <CanvasJSChart options = {options} />
            </div>
		);
	}
}

export default DoughnutChart;

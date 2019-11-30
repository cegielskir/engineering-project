import React, { Component } from "react";
import Dropzone from "../Dropzone/Dropzone";
import "./Upload.css";
import Progress from "../Progress/Progress";
import fileSVG  from 'assets/img/file.svg';
import uploadSVG  from 'assets/img/upload.svg';
import loadingSVG  from 'assets/img/refresh-button.svg';
import errorSVG  from 'assets/img/error.svg';
import successSVG  from 'assets/img/success.svg';

class Upload extends Component {
  constructor(props) {
    super(props);
    this.state = {
      files: [],
      uploading: false,
      uploadProgress: {},
      successfullUploaded: false,
      uploadState: 0,
    };

    this.onFilesAdded = this.onFilesAdded.bind(this);
    this.uploadFiles = this.uploadFiles.bind(this);
    this.sendRequest = this.sendRequest.bind(this);
    this.renderActions = this.renderActions.bind(this);
  }

  onFilesAdded(files) {
    this.setState(prevState => ({
      files: prevState.files.concat(files)
    }));
  }

  async uploadFiles() {
    this.setState({ uploadProgress: {}, uploading: true, uploadState: 1 });
    const promises = [];
    this.state.files.forEach(file => {
      promises.push(this.sendRequest(file));
    });
    try {
      await Promise.all(promises);

      this.setState({ successfullUploaded: true, uploading: false, uploadState: 3});
    } catch (e) {
      console.log(e);
      console.log('error occured')
      // Not Production ready! Do some error handling here instead...
      this.setState({ successfullUploaded: true, uploading: false, uploadState: 4});
    }
  }

  sendRequest(file) {
    return new Promise((resolve, reject) => {
      const req = new XMLHttpRequest();

      req.upload.addEventListener("progress", event => {
        if (event.lengthComputable) {
          const copy = { ...this.state.uploadProgress};
          copy[file.name] = {
            state: "pending",
            percentage: (event.loaded / event.total) * 100
          };
          this.setState({ uploadProgress: copy, uploadState: 2 });
        }
      });

      req.upload.addEventListener("load", event => {
        const copy = { ...this.state.uploadProgress };
        copy[file.name] = { state: "done", percentage: 100 };
        this.setState({ uploadProgress: copy });
        resolve(req.response);
      });

      req.upload.addEventListener("error", event => {
        const copy = { ...this.state.uploadProgress };
        copy[file.name] = { state: "error", percentage: 0 };
        this.setState({ uploadProgress: copy });
        reject(req.response);
      });

      const formData = new FormData();
      formData.append("file", file, file.name);

      req.open("POST", "http://localhost:8060/core/excel/uploadFile");
      req.send(formData);
    });
  }

  renderProgress(file) {
    const uploadProgress = this.state.uploadProgress[file.name];
    if (this.state.uploading || this.state.successfullUploaded) {
      return (
        <div className="ProgressWrapper">
          <Progress progress={uploadProgress ? uploadProgress.percentage : 0} />
        </div>
      );
    }
  }

  renderActions() {
    if (this.state.successfullUploaded) {
      return (
        <button
          className={'upload-button'}
          onClick={() =>
            this.setState({ files: [], successfullUploaded: false, uploadState: 0,})
          }
        >
          Clear
        </button>
      );
    } else {
      return (
        <button
          className={'upload-button'}
          disabled={this.state.files.length < 0 || this.state.uploading}
          onClick={this.uploadFiles}
        >
          Upload
        </button>
      );
    }
  }

  render() {
    let icon = fileSVG;
    switch(this.state.uploadState) {
      case 0: icon = fileSVG; break;
      case 1: icon = uploadSVG; break;
      case 2: icon = loadingSVG; break;
      case 3: icon = successSVG; break;
      case 4: icon = errorSVG; break;
      default: icon = fileSVG; break;
    }

    return (
      <div className="Upload">
        <div className="Content">
          <div>
            <Dropzone
              onFilesAdded={this.onFilesAdded}
              disabled={this.state.uploading || this.state.successfullUploaded}
            />
          </div>

          <div className="Files">
            <span className="Title">Upload Files list</span>
            {this.state.files.map(file => {
              return (
                <div key={file.name} className="Row">
                  <div>
                    <span className="Filename">
                      <img className="fileIcon" alt="file-icon" src={icon} />
                      {file.name}
                    </span>
                  </div>
                  <div className="progress-wrapper">
                    {this.renderProgress(file)}
                  </div>
                </div>
              );
            })}
          </div>
        </div>
        <div className="Actions">{this.renderActions()}</div>
      </div>
    );
  }
}

export default Upload;

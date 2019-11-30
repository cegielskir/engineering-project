import * as React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Switch
} from "react-router-dom";
import * as P from './parts';
import './App.css';

import Home from './modules/HomePage/HomePage';
import Comparison from './modules/ComparisonPage/ComparisonPage';
import Result from './modules/ResultPage/ResultPage';
import ResultList from  './modules/ResultListPage/ResultList';
import FilesUpload from  './modules/FilesUploadPage/FilesUploadPage';
import NotFound from './modules/NotFound/NotFound';
import WSResultList from './modules/WSResultListPage/WSResultList';
import AddSingleArticle from 'modules/AddSingleArticle/AddSingleArticle';
import FindArticle from 'modules/FindSingleArticle/FindArticle';

import Menu from './components/Menu/Menu'
import Footer from  './components/Footer/Footer'


function App() {
  return (
    <>      
        <Router>
          <Menu />
          <P.PageWrapper>
            <Switch>
              <Route exact path="/" component={Home} />
              <Route path="/comparison" component={Comparison} />
              <Route path="/result/:comparisonId1/:comparisonId2" component={Result} />
              <Route path="/result-list/:pageNumber" component={ResultList} />
              <Route path="/add-file" component={FilesUpload} />
              <Route path="/add-article" component={AddSingleArticle} />
              <Route path="/find-article" component={FindArticle} />
              <Route path="/socket-connection" component={WSResultList} />
              <Route component={NotFound} />
            </Switch>
          </P.PageWrapper>
        </Router>
      <Footer />
    </>
  );
}

export default App;

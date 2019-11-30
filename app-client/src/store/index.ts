import { createStore, combineReducers, applyMiddleware } from "redux";
import thunkMiddleware from "redux-thunk";
import { composeWithDevTools } from "redux-devtools-extension";

import { articleReducer } from "./SingleArticle/reducers";
import { twoArticlesReducer } from "./TwoArticles/reducers";
import { comparisonReducer } from './DetailsResult/reducers';
import { comparisonsWsListReducer } from './websockets/reducers';
import { searchWsListReducer } from './ws_search_result/reducers';

const rootReducer = combineReducers({
    singleArticle: articleReducer,
    comparison: comparisonReducer,
    twoArticles: twoArticlesReducer,
    ws: comparisonsWsListReducer,
    sa: searchWsListReducer,
});

export type AppState = ReturnType<typeof rootReducer>;

export default function configureStore() {
  const middlewares = [thunkMiddleware];
  const middleWareEnhancer = applyMiddleware(...middlewares);

  const store = createStore(
    rootReducer,
    composeWithDevTools(middleWareEnhancer)
  );

  return store;
}

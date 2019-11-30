import { ArticleAction } from './actions';
import { StoreState } from './store';
import { ADD_ARTICLE } from './constants';

const initialState: StoreState = {
    singleArticle: {
        id: '',
        content: '',
        metrics: [],
        threshold: 0,
    }
}

export function articleReducer(state = initialState, action: ArticleAction): StoreState {
    /* here will be other actions */
    switch (action.type) {
        case ADD_ARTICLE:
            return { 
                ...state, 
                singleArticle: {
                    id: action.article.id, 
                    content: action.article.content, 
                    metrics: action.article.metrics,
                    threshold: action.article.threshold,
                }
            };
        default: 
            return state;
    }
}

export default articleReducer;

export const getComparisonState = (state: StoreState) => state.singleArticle;

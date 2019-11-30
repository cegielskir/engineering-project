import { ArticlesAction } from './actions';
import { TwoArticlesState } from './store';
import { ADD_TWO_ARTICLES } from './constants';

const initialState: TwoArticlesState = {
    twoArticles: {
        article1: {
            id: '',
            content: '',
            metrics: [],
        },
        article2: {
            id: '',
            content: '',
            metrics: [],
        }
    }
}

export function twoArticlesReducer(state = initialState, action: ArticlesAction): TwoArticlesState {
    /* here will be other actions */
    switch (action.type) {
        case ADD_TWO_ARTICLES:
            return { 
                ...state, 
                twoArticles: {
                    article1: {
                        id: action.article1.id,
                        content: action.article1.content,
                        metrics: [],
                    },
                    article2: {
                        id: action.article2.id,
                        content: action.article2.content,
                        metrics: [],
                    }
                }
            };
        default: 
            return state;
    }
}

export default twoArticlesReducer;

export const getComparisonState = (state: TwoArticlesState) => state.twoArticles;

import { ComparisonAction } from './actions';
import { ComparisonState } from './store';
import { GET_COMPARISON, GET_COMPARISON_SUCCESS, GET_COMPARISON_ERROR } from './constants';

const initialState: ComparisonState = {
    pending: false,
    comparison: {
        id: 0,
        percentage: 0,
        similarityPercentage: 0,
        suspiciousWords: [],
        firstArticleContent:'',
        secondArticleContent: '',
    },
    error: '',
}

export function comparisonReducer(state = initialState, action: ComparisonAction): ComparisonState {
    /* here will be other actions */
    switch (action.type) {
        case GET_COMPARISON: 
            return {
                ...state,
                pending: true
            }
        case GET_COMPARISON_SUCCESS:
            return {
                ...state,
                pending: false,
                comparison: action.comparison,
            }
        case GET_COMPARISON_ERROR:
            return {
                ...state,
                pending: false,
                error: action.error
            }
        default: 
            return state;
    }
}

export default comparisonReducer;

export const getComparisonState = (state: ComparisonState) => state.comparison;
export const getComparisonPending = (state: ComparisonState) => state.pending;
export const getComparisonError = (state: ComparisonState) => state.error;

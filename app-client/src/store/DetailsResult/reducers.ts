import { ComparisonAction } from './actions';
import { ComparisonState } from './store';
import { GET_COMPARISON, GET_COMPARISON_SUCCESS, GET_COMPARISON_ERROR } from './constants';

const initialState: ComparisonState = {
    pending: false,
    comparison: {
        percentage: 0,
        similarityPercentage: 0,
        article1: {
            id: '',
            title: '',
            author: '',
            description: '',
            content: '',
            date: new Date(),
            url: '',
            numberOfView: 0,
            hash: 0,
            downloadTime: '',
        },
        article2:{
            id: '',
            title: '',
            author: '',
            description: '',
            content: '',
            date: new Date(),
            url: '',
            numberOfView: 0,
            hash: 0,
            downloadTime: '',
        },
        suspiciousWords: [],
        metric: '',
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

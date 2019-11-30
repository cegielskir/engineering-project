import * as A from './actions';
import { WebSocketState } from './store';
import * as C from './constants';

const initialState: WebSocketState = {
    pending: false,
    comparisons: [],
    error: '',
}

const updateList = (currentList: A.ComparisonList[], newItem: A.ComparisonList | undefined) => {
    return newItem ? currentList.concat(newItem) : currentList;
}

export function comparisonsWsListReducer(state = initialState, action: A.GetComparisonAction): WebSocketState {
    switch (action.type) {
        case C.GET_COMPARISON_LIST_REQUEST: 
            return {
                ...state,
                comparisons: [],
                pending: true
            }
        case C.GET_COMPARISON_LIST_SUCCESS:
            return {
                ...state,
                pending: false,
                comparisons: updateList(
                    state.comparisons ? state.comparisons : [],
                    action.comparison 
                )
            }
        case C.GET_COMPARISON_LIST_FAILURE:
            return {
                ...state,
                pending: false,
                error: action.error
            }
        default: 
            return state;
    }
}

export default comparisonsWsListReducer;

export const getComparisonListState = (state: WebSocketState) => state.comparisons;
export const getComparisonListPending = (state: WebSocketState) => state.pending;
export const getComparisonListError = (state: WebSocketState) => state.error;

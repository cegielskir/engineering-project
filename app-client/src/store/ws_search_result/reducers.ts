import * as A from './actions';
import { SearchState } from './store';
import * as C from './constants';

const initialState: SearchState = {
    pending: false,
    results: [],
    error: '',
}

const updateList = (currentList: A.Article[], newItem: A.Article | undefined) => {
    return newItem ? currentList.concat(newItem) : currentList;
}

export function searchWsListReducer(state = initialState, action: A.GetSearchAction): SearchState {
    switch (action.type) {
        case C.GET_SEARCH_LIST_REQUEST: 
            return {
                ...state,
                results: [],
                pending: true
            }
        case C.GET_SEARCH_LIST_SUCCESS:
            return {
                ...state,
                pending: false,
                results: updateList(
                    state.results ? state.results : [],
                    action.article
                )
            }
        case C.GET_SEARCH_LIST_FAILURE:
            return {
                ...state,
                pending: false,
                error: action.error
            }
        default: 
            return state;
    }
}

export default searchWsListReducer;

export const getComparisonListState = (state: SearchState) => state.results;
export const getComparisonListPending = (state: SearchState) => state.pending;
export const getComparisonListError = (state: SearchState) => state.error;

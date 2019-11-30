import * as A from './actions';
import * as AS from '../ws_search_result/actions';
import { Dispatch } from 'redux';
import { ComparisonList } from "./actions";
import { Article } from "../ws_search_result/actions";

export function fetchComparisonList(comparison: ComparisonList) {
    return (dispatch: Dispatch) => {
        if(!!comparison) {
            dispatch(A.getComparisonSuccess(comparison));
        } else {
            dispatch(A.getComparisonFailure('invalid data'))
        }
    }
}

export function clearStore() {
    return (dispatch: Dispatch) => {
        dispatch(AS.getSearchRequest());
    }
}

export function fetchSearchList(article: Article) {
    return (dispatch: Dispatch) => {
        if(!!article) {
            dispatch(AS.getSearchSuccess(article));
        } else {
            dispatch(AS.getSearchFailure('invalid data'))
        }
    }
}

export function clearWSStore() {
    return (dispatch: Dispatch) => {
        dispatch(A.getComparisonRequest());
    }
}
import { API_BASE_URL } from 'constants/constants';
import { Dispatch } from 'redux';
import * as AD from 'store/DetailsResult/actions';

export function getData(url: string) {

    return fetch(API_BASE_URL+url)
        .then(response => {
            if(response.ok){
                return response;
            }
            throw Error("Fetch error")
        })
        .then(response => response.json())
        .catch(err => err);
}

export function getMethods() {

    return fetch(API_BASE_URL+'/core/method/basic')
        .then(response => {
            if(response.ok){
                return response;
            }
            throw Error("Fetch error")
        })
        .then(response => response.json())
        .catch(err => err);
}

export function postData(url: string, bodyContent: any) {
    return fetch(API_BASE_URL+url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(bodyContent)
    })
    .then(response => {
        if(response.ok){
            return response;
        }
        throw Error("Fetch error")
    })
    .then(response => response.json())
    .catch(err => err);
}


export function fetchDetailsResult(url: string) {
    return (dispatch: Dispatch) => {
        dispatch(AD.getComparison());
        fetch(API_BASE_URL+url)
        .then(res => res.json())
        .then(res => {
            if(res.error) {
                throw(res.error);
            }
            dispatch(AD.getComparisonSuccess(res));
            return res;
        })
        .catch(error => {
            dispatch(AD.getComparisonError(error));
        })
    }
}

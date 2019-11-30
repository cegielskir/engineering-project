import * as C from './constants';

export interface Article {
    id: string;
    title: string;
    author: string;
    description: string;
    content: string;
    date: string;
    url: string;
    numberOfViews: string;
    hash: number;
    downloadTime: string;
}

interface GetSearchRequest {
    type: C.GET_SEARCH_LIST;
    article: Article;
    error?: string;   
}

export const getSearchRequest = () => ({
  type: C.GET_SEARCH_LIST_REQUEST,
});
export const getSearchSuccess = (data: Article) => {
  return {
    type: C.GET_SEARCH_LIST_SUCCESS,
    article: data
  };
};
export const getSearchFailure = (err: string) => ({
  type: C.GET_SEARCH_LIST_FAILURE,
  error: err
});

export type GetSearchAction = GetSearchRequest;
import * as C from './constants';

export interface ComparisonList {
  secondArticleID: string;
  secondArticleShortContent: string;
  secondArticleTitle: string;
  secondArticleDescription: string;
  comparisonMap: Map<string, number>;
}

interface GetComparisonRequest {
    type: C.GET_COMPARISON_LIST;
    comparison: ComparisonList;
    error?: string;   
}

export const getComparisonRequest = () => ({
  type: C.GET_COMPARISON_LIST_REQUEST,
});
export const getComparisonSuccess = (data: ComparisonList) => {
  return {
    type: C.GET_COMPARISON_LIST_SUCCESS,
    comparison: data
  };
};
export const getComparisonFailure = (err: string) => ({
  type: C.GET_COMPARISON_LIST_FAILURE,
  error: err
});

export type GetComparisonAction = GetComparisonRequest;

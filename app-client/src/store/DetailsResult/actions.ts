import * as C from './constants';

export interface SuspiciousFragments {
    firstArticleStart: number;
    firstArticleEnd: number;
    secondArticleStart: number;
    secondArticleEnd: number;
}

export interface Comparison {
    id: number;
    percentage: number;
    similarityPercentage: number;
    firstArticleContent: string;
    secondArticleContent: string;
    suspiciousWords: SuspiciousFragments[];
}

interface GetComparisonRequest {
    type: C.GET_COMPARISON_REQUEST;
    comparison?: Comparison;
    error?: string;   
}

export const getComparison = () => {
    return {
        type: C.GET_COMPARISON,
    }
};

export const getComparisonSuccess = (data: Comparison): GetComparisonRequest => {
    return {
        type: C.GET_COMPARISON_SUCCESS,
        comparison: data,
    }
};

export const getComparisonError = (err: string): GetComparisonRequest => {
    return {
        type: C.GET_COMPARISON_ERROR,
        error: err,
    }
};

export type ComparisonAction = GetComparisonRequest;

import * as C from './constants';

export interface Article {
    id: string;
    title: string;
    author: string;
    description: string;
    content: string;
    date: Date;
    url: string;
    numberOfView: number;
    hash: number;
    downloadTime: string;
}

export interface SuspiciousFragments {
    firstArticleStart: number;
    firstArticleEnd: number;
    secondArticleStart: number;
    secondArticleEnd: number;
}

export interface Comparison {
    percentage: number;
    similarityPercentage: number;
    article1: Article;
    article2: Article;
    suspiciousWords: SuspiciousFragments[];
    metric: string;
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

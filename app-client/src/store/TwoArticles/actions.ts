import * as C from './constants';

export interface Article {
    id: string;
    content: string;    
    metrics: string[];
}

export interface AddTwoArticles {
    type: C.ADD_TWO_ARTICLES;
    article1: Article;
    article2: Article;
}

export const addTwoArticles = (article1: Article, article2: Article): AddTwoArticles => ({
    type: C.ADD_TWO_ARTICLES,
    article1: article1,
    article2: article2,
});

// this line was created for future usage
export type ArticlesAction = AddTwoArticles;

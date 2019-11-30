import * as C from './constants';

export interface Article {
    id: string;
    content: string;
    metrics: string[];
    threshold: number;
}

export interface AddArticle {
    type: C.ADD_ARTICLE;
    article: Article;
}

export const addArticle = (article: Article): AddArticle => ({
    type: C.ADD_ARTICLE,
    article: article
});

// this line was created for future usage
export type ArticleAction = AddArticle;

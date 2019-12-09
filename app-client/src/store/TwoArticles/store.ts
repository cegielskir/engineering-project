import { Article } from "./actions";

export interface TwoArticlesState {
    twoArticles: {
        article1: Article;
        article2: Article;
        metrics: string[];
    };
} 
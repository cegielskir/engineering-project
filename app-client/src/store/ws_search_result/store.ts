import { Article} from './actions';

export interface SearchState {
    pending: boolean;
    results: Article[];
    error?: string;
} 
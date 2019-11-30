import { ComparisonList } from './actions';

export interface WebSocketState {
    pending: boolean;
    comparisons: ComparisonList[];
    error?: string;
} 
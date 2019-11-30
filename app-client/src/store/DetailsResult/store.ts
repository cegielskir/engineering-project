import { Comparison } from './actions';

export interface ComparisonState {
    pending: boolean;
    comparison: Comparison | undefined;
    error: string | undefined;
}

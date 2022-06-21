export interface PageofItems<T> {
    items: T[];
    hasNext: boolean;
    totalElements: number;
}

export interface ReponseAPI<T>{
    status: number,
    error: string,
    data: T
}
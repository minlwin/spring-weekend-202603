// Literal Types
export type Yes = 'Yes'
export type No = 'No'

// Union Type
export type Decision = Yes | No

// Interface Type
export interface Pager {
    size: number
    page: number
    totalCount: number
    totalPage: number
}

export type List<T> = {
    contents: T[]
} 

// Intersection Types
export type PageResult<T> = List<T> & Pager

// Interface Type
export interface User {
    name: string
    age: number
    job?: string
}

export function checkUser(user: User): string {
    return `${user.name} is ${user.age} years old.`;
}
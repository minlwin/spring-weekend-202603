import React from "react"

export * from "./forms"
export * from "./authentication"
export * from "./management/employee-management"

export type LayoutsProps = {
    children : Readonly<React.ReactNode>
}

export type FormParams = {
    [key: string] : any
}

export type ClientError = {
    status : number
    messages: string[]
}

export type ClientRequest = {
    path: string, 
    method: 'get' | 'post' | 'put' | 'patch', 
    params?: FormParams, 
    useFile? : boolean
}

export type ModificationResult<T> = {
    id: T
}
import React from "react"

export * from "./forms"
export * from "./authentication"

export type LayoutsProps = {
    children : Readonly<React.ReactNode>
}

export type SearchParams = {
    [key: string] : any
}

export type FormParams = {
    [key: string] : any
}

export type ClientError = {
    status : number
    messages: string[]
}
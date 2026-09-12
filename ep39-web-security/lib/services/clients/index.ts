import { ClientError, FormParams, SearchParams } from '@/lib/types'
import 'server-only'

export async function get<T>(path: string, params?: SearchParams) : Promise<T> {
    const response = await fetch(url(path, params))

    if(!response.ok) {
        const errorResponse:ClientError = {
            status: response.status,
            messages: await response.json()
        }

        throw Error(JSON.stringify(errorResponse))
    }

    return await response.json()
}

export async function post<T>(path: string, params?: FormParams, useFile : boolean = false) : Promise<T> {
    return await request(path, 'post', params, useFile)
}

export async function put<T>(path: string, params?: FormParams, useFile : boolean = false) : Promise<T> {
    return await request(path, 'put', params, useFile)
}

export async function patch<T>(path: string, params?: FormParams, useFile : boolean = false) : Promise<T> {
    return await request(path, 'patch', params, useFile)
}

async function request<T>(path: string, method: string, params?: FormParams, useFile : boolean = false) : Promise<T> {

    const response = await fetch(url(path), getRequestInit(method, params, useFile))

    if(!response.ok) {
        const errorResponse:ClientError = {
            status: response.status,
            messages: await response.json()
        }

        throw Error(JSON.stringify(errorResponse))
    }

    return await response.json()
}

export function securedGet() {

}

function getRequestInit(method: string, params?: FormParams, useFile : boolean = false) : RequestInit {

    if(!params) {
        return {
            method: method,
        }
    }

    return {
        method: method,
        headers: !useFile ? {
            'Content-Type' : 'application/json'
        } : undefined,
        body: useFile? getFormData(params!) : JSON.stringify(params)
    }
}

function getFormData(params: FormParams) :FormData {
    const form = new FormData

    for(const [key, value] of Object.entries(params)) {

    }

    return form
}

function url(path: string, params? : SearchParams) {
    const baseUrl = process.env.BASE_API

    if(params) {
        const searchParams = new URLSearchParams(params)
        return `${baseUrl}/${path}?${searchParams.toString()}`
    }

    return `${baseUrl}/${path}`
}
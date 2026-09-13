import { AuthResult, ClientError, FormParams, SearchParams } from '@/lib/types'
import 'server-only'
import * as security from "@/lib/services/security/security-context"

export async function request<T>(path: string, method: string, params?: FormParams, useFile : boolean = false) : Promise<T> {

    const response = method == 'get' ? await fetch(url(path, params)) : await fetch(url(path), getRequestInit(method, params, useFile))

    if(!response.ok) {
        const errorResponse:ClientError = {
            status: response.status,
            messages: await response.json()
        }

        throw Error(JSON.stringify(errorResponse))
    }

    return await response.json()
}

export async function securedRequest<T>(path: string, method: string, params?: FormParams, useFile : boolean = false): Promise<T> {

    async function requestWithToken(token: string) {
        const requestUrl = method == 'get' ? url(path, params) : path
        const requestInt = getRequestInit(method, params, useFile)        
        return await fetch(requestUrl, {
            ...requestInt,
            headers: {
                ...requestInt.headers,
                'Authorization' : token
            }
        })
    }

    const accessToken = await security.getAccessToken()

    if(!accessToken) {
        await security.clearContext()
        const error:ClientError = {
            status: 401,
            messages: ["You have to sign in for this operation."]
        }
        throw new Error(JSON.stringify(error))
    }

    let response = await requestWithToken(accessToken)

    if(response.status === 410) {
        const token = await security.getRefreshToken()
        const refreshResult:AuthResult = await request('auth/token/refresh', 'post', {
            token : token
        })
        const {accessToken, refreshToken, ...loginUser} = refreshResult

        await security.login(accessToken, refreshToken, loginUser)

        response = await requestWithToken(accessToken)
    }

    if(!response.ok) {
        if(response.status === 401 || response.status === 403) {
            await security.clearContext()
        }

        const errorResponse:ClientError = {
            status: response.status,
            messages: await response.json()
        }

        throw Error(JSON.stringify(errorResponse))
    }


    return await response.json()
}

function getRequestInit(method: string, params?: FormParams, useFile : boolean = false) : RequestInit {

    if(!params || method == 'get') {
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
        form.append(key, value)
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
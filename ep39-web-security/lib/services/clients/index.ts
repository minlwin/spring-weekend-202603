import { AuthResult, ClientError, ClientRequest, FormParams } from '@/lib/types'
import 'server-only'
import * as security from "@/lib/services/storage/security-context"

export async function publicRequest<T>(request: ClientRequest) : Promise<T> {

    const response = request.method == 'get' ? 
        await fetch(url(request.path, request.params)) : 
        await fetch(url(request.path), getRequestInit(request))

    if(!response.ok) {
        const errorResponse:ClientError = {
            status: response.status,
            messages: await response.json()
        }

        throw Error(JSON.stringify(errorResponse))
    }

    return await response.json()
}

export async function securedRequest<T>(request: ClientRequest): Promise<T> {

    async function requestWithToken(token: string) {
        const requestUrl = request.method == 'get' ? url(request.path, request.params) : url(request.path)
        const requestInt = getRequestInit(request)        
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
        const refreshResult:AuthResult = await publicRequest({
            path: 'auth/token/refresh', 
            method: 'post', 
            params : { token : token }
        })

        await security.login(refreshResult)
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

function getRequestInit(request: ClientRequest) : RequestInit {

    if(!request.params || request.method == 'get') {
        return {
            method: request.method,
        }
    }

    return {
        method: request.method,
        headers: !request.useFile ? {
            'Content-Type' : 'application/json'
        } : undefined,
        body: request.useFile? getFormData(request.params!) : JSON.stringify(request.params)
    }
}

function getFormData(params: FormParams) :FormData {
    const form = new FormData

    for(const [key, value] of Object.entries(params)) {
        form.append(key, value)
    }

    return form
}

function url(path: string, params? : FormParams) {
    const baseUrl = process.env.BASE_API

    if(params) {
        const searchParams = new URLSearchParams(params)
        return `${baseUrl}/${path}?${searchParams.toString()}`
    }

    return `${baseUrl}/${path}`
}
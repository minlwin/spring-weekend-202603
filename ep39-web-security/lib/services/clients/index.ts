import { AuthResult, ClientError, ClientRequest, FormParams } from '@/lib/types'
import 'server-only'
import * as security from "@/lib/services/storage/security-context"
import { redirect } from 'next/navigation'

export async function publicRequest<T>(request: ClientRequest) : Promise<T> {

    const response = request.method == 'get' ? 
        await fetch(url(request.path, request.params)) : 
        await fetch(url(request.path), getRequestInit(request))

    if(!response.ok) {
        if(response.status === 401 || response.status === 403) {
            redirect(`/signin?message=Session timeout. Please login again.`)
        }
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
        redirect('/signin?message=You have to sign in for this operation.')
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
        response = await requestWithToken(refreshResult.accessToken)
    }

    if(!response.ok) {
        const messages:string[] = await response.json()

        if(response.status === 401 || response.status === 403) {
            await security.clearContext()
            redirect(`/signin?message=${messages[0] || 'You have to login again.'}`)
        } 

        const errorResponse:ClientError = {
            status: response.status,
            messages: messages
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
        headers: {
            'Content-Type' : request.useFile ? 'application/x-www-form-urlencoded' : 'application/json'
        },
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
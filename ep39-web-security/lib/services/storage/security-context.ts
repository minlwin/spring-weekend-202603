import { AuthResult, LoginUser } from '@/lib/types';
import { ResponseCookie } from 'next/dist/compiled/@edge-runtime/cookies';
import { cookies } from 'next/headers';
import 'server-only'

export async function login(authResult : AuthResult) {
    const {accessToken, refreshToken, ... loginUser} = authResult
    const cookieStore = await cookies()
    const props : Partial<ResponseCookie> = {
        httpOnly: true,
        maxAge: 30 * 60,
        sameSite: 'strict',
        secure: process.env.NODE_ENV === 'production'
    }

    cookieStore.set('accessToken', accessToken, {...props})
    cookieStore.set('refreshToken', refreshToken, {...props})
    cookieStore.set('loginUser', JSON.stringify(loginUser), {...props})
}

export async function clearContext() {
    const cookieStore = await cookies()
    cookieStore.delete('accessToken')
    cookieStore.delete('refreshToken')
    cookieStore.delete('loginUser')
    cookieStore.delete('feature')
}

export async function getAccessToken() : Promise<string | undefined> {
    const cookieStore = await cookies()
    return cookieStore.get('accessToken')?.value
}

export async function getRefreshToken() : Promise<string | undefined> {
    const cookieStore = await cookies()
    return cookieStore.get('refreshToken')?.value
}

export async function getLoginUser() : Promise<LoginUser | undefined> {
    const cookieStore = await cookies()
    const value = cookieStore.get('loginUser')?.value
    if(value) {
        return JSON.parse(value)
    }
}

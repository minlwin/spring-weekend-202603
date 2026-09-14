import { ResponseCookie } from "next/dist/compiled/@edge-runtime/cookies"
import { cookies } from "next/headers"

export async function setFeature(feature: string) {
    const cookieStore = await cookies()
    const props : Partial<ResponseCookie> = {
        httpOnly: true,
        maxAge: 30 * 60,
        sameSite: 'strict',
        secure: process.env.NODE_ENV === 'production'
    }

    cookieStore.set('feature', feature, {...props})
}

export async function getFeature() : Promise<string | undefined> {
    const cookieStore = await cookies()
    return cookieStore.get('feature')?.value
}

'use server'

import { LoginUser, SignInForm } from "@/lib/types";
import * as client from "@/lib/services/clients/auth-token.client"
import * as security from "@/lib/services/security/security-context"

export async function signIn(form: SignInForm) : Promise<LoginUser> {
    const {accessToken, refreshToken, ... loginUser} = await client.generate(form)

    // Store Tokens
    security.login(accessToken, refreshToken, loginUser)

    return loginUser
}
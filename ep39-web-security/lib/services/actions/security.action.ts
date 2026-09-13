'use server'

import { SignInForm } from "@/lib/types";
import * as client from "@/lib/services/clients/auth-token.client"
import * as security from "@/lib/services/security/security-context"
import { redirect } from "next/navigation";
import { getHome } from "@/lib/utils";

export async function signIn(form: SignInForm) : Promise<void> {
    const {accessToken, refreshToken, ... loginUser} = await client.generate(form)

    // Store Tokens
    await security.login(accessToken, refreshToken, loginUser)

    // Redirect to Home
    redirect(getHome(loginUser.role))
}

export async function signOut() {
    await security.clearContext()
    redirect("/")
}
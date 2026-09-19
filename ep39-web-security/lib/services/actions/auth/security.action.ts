'use server'

import { SignInForm, SignUpForm } from "@/lib/types";
import * as client from "@/lib/services/clients/auth/auth-token.client"
import * as security from "@/lib/services/storage/security-context"
import * as signUpClient from '@/lib/services/clients/auth/signup.client'
import { redirect } from "next/navigation";
import { getHome } from "@/lib/utils";

export async function signIn(form: SignInForm) : Promise<void> {
    const authResult = await client.generate(form)

    // Store Tokens
    await security.login(authResult)

    // Redirect to Home
    redirect(getHome(authResult.role))
}

export async function signUp(form: SignUpForm) {
    const result = await signUpClient.signUp(form)
    redirect(`/activation?message=${result.message}`)
}

export async function signOut() {
    await security.clearContext()
    redirect("/")
}

'use server'

import { ActivationForm, ResendOtpForm } from "@/lib/types";
import { getHome } from "@/lib/utils";

import * as client from "@/lib/services/clients/auth/account-activation.client"
import * as security from "@/lib/services/storage/security-context"
import { redirect } from "next/navigation";

export async function activationAction(form : ActivationForm) {
    const authResult = await client.activate(form)

    // Store Tokens
    await security.login(authResult)

    // Redirect to Home
    redirect(getHome(authResult.role))
}

export async function resendOptAction(form : ResendOtpForm) {
    await client.resend(form)
    redirect(`/activation?message=OTP has been send to your email. Please activate your account.`)
}
'use server'

import { ForgotPasswordForm, ResetPasswordForm } from "@/lib/types";
import * as client from "@/lib/services/clients/auth/password-recovery.client"
import { redirect } from "next/navigation";

export async function forgotPasswordRequest(form : ForgotPasswordForm) {
    const { id } = await client.requestForgotPassword(form)
    redirect(`/password/${id}`)
}

export async function resendOtpRequest(form : ForgotPasswordForm) {
    const { id } = await client.resend(form)
    redirect(`/password/${id}`)
}

export async function resetPassword(id: any, form : ResetPasswordForm) {
    const result = await client.resetPassword(id, form)
    redirect(`/signin?message=${result.id}`)
}

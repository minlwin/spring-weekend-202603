import { ForgotPasswordForm, ModificationResult, ResetPasswordForm } from "@/lib/types";
import { publicRequest } from "..";

const ENDPOINT = 'password'

export async function requestForgotPassword(form: ForgotPasswordForm) : Promise<ModificationResult<string>> {
    return await publicRequest({
        path: `${ENDPOINT}/forgot`,
        method: 'post',
        params: form
    })
}

export async function resend(form: ForgotPasswordForm) : Promise<ModificationResult<string>> {
    return await publicRequest({
        path: `${ENDPOINT}/resend`,
        method: 'post',
        params: form
    })
}

export async function resetPassword(id: any, form: ResetPasswordForm) : Promise<ModificationResult<string>> {
    return await publicRequest({
        path: `${ENDPOINT}/${id}/reset`,
        method: 'post',
        params: form
    })
}
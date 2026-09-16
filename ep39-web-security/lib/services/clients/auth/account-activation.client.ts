import { ActivationForm, AuthResult, ResendOtpForm } from "@/lib/types";
import { publicRequest } from "..";

const ENDPOINT = 'auth/activate'

export async function activate(form: ActivationForm) : Promise<AuthResult>{
    return await publicRequest({
        path: ENDPOINT,
        method: 'post',
        params: form
    })
}

export async function resend(form: ResendOtpForm) : Promise<string> {
    return await publicRequest({
        path: `${ENDPOINT}/resend`,
        method: 'post',
        params: form
    })
}
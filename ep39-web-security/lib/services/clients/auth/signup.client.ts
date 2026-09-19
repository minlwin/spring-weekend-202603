import { SignUpForm, SignUpResult } from "@/lib/types";
import { publicRequest } from "..";

const ENDPOINT = "auth/signup"

export async function signUp(form: SignUpForm):Promise<SignUpResult> {
    return await publicRequest({
        path: ENDPOINT,
        method: 'post',
        params: form
    })
}
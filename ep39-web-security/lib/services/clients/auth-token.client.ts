import { AuthResult, SignInForm } from "@/lib/types";
import { request } from ".";

const PATH = 'auth/token'

export async function generate(form : SignInForm) : Promise<AuthResult> {
    return await request(`${PATH}/generate`, 'post', form)
}
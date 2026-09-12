import { AuthResult, SignInForm } from "@/lib/types";
import { post } from ".";

const PATH = 'auth/token'

export async function generate(form : SignInForm) : Promise<AuthResult> {
    return await post(`${PATH}/generate`, form)
}
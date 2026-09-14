import { AuthResult, SignInForm } from "@/lib/types";
import { publicRequest } from ".";

const PATH = 'auth/token'

export async function generate(form : SignInForm) : Promise<AuthResult> {
    return await publicRequest({
        path : `${PATH}/generate`, 
        method : 'post', 
        params : form
    })
}
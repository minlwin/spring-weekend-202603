import z from "zod";

export const SignInSchema = z.object({
    email: z.string().nonempty("Please enter email.").email("Please enter valid email."),
    password: z.string().nonempty("Please enter password.")
})

export type SignInForm = z.infer<typeof SignInSchema>

export const SignUpSchema = z.object({
    type: z.string().nonempty("Please select type."),
    name: z.string().nonempty("Please enter your name."),
    email: z.string().nonempty("Please enter email.").email("Please enter valid email."),
})

export type SignUpForm = z.infer<typeof SignUpSchema>

export type SignUpResult = {
    message : string
}

export type Role = 'Admin' | 'Employee' | 'Candidate'  | 'Partner' | 'Member'

export type AuthResult = {
    name: string
    email: string
    role: Role
    accessToken: string
    refreshToken: string
}

export type LoginUser = Omit<AuthResult, 'accessToken' | 'refreshToken' >
import z from "zod"

export const SignInSchema = z.object({
    "email" : z.string()
        .nonempty("Please enter email")
        .email("Please enter valid email"),
    "password" : z.string()
        .nonempty("Please enter password")
})

export type SignInForm = z.infer<typeof SignInSchema>

export const SignUpSchema = z.object({
    "name" : z.string()
        .nonempty("Please enter user name"),
    "email" : z.string()
        .nonempty("Please enter email")
        .email("Please enter valid email"),
    "password" : z.string()
        .nonempty("Please enter password")
})

export type SignUpForm = z.infer<typeof SignUpSchema>

export type Role = 'Admin' | 'Student'  

export type User = {
    name: string
    email: string
    password: string
    role: Role
}

export type LoginResult = Omit<User, "password">
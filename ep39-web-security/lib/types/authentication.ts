import z from "zod";

export const SignInSchema = z.object({
    email: z.string().nonempty("Please enter email.").email("Please enter valid email."),
    password: z.string().nonempty("Please enter password.")
})

export type SignInForm = z.infer<typeof SignInSchema>

export const SignUpSchema = z.object({
    name: z.string().nonempty("Please enter your name."),
    email: z.string().nonempty("Please enter email.").email("Please enter valid email."),
})
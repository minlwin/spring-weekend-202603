import z from "zod"

export const ForgotPasswordSchema = z.object({
    email: z.string().nonempty("Please enter email.").email("Please enter valid email."),
})

export type ForgotPasswordForm = z.infer<typeof ForgotPasswordSchema>

export const ResetPasswordSchema = z.object({
    securityCode: z.string().nonempty("Please enter OTP Code."),
    password: z.string().nonempty("Please enter password.")
})

export type ResetPasswordForm = z.infer<typeof ResetPasswordSchema>
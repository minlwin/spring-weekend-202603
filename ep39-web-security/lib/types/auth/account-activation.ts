import z from "zod";

export const ActivationShcma = z.object({
    email: z.string().nonempty("Please enter email.").email("Please enter valid email."),
    securityCode: z.string().nonempty("Please enter OTP Code."),
    password: z.string().nonempty("Please enter password.")
})

export type ActivationForm = z.infer<typeof ActivationShcma>

export const ResendOtpSchema = z.object({
    email: z.string().nonempty("Please enter email.").email("Please enter valid email."),
})

export type ResendOtpForm = z.infer<typeof ResendOtpSchema>
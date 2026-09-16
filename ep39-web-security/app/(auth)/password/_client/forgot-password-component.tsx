'use client'

import { FormsInput } from "@/components/forms"
import { Button } from "@/components/ui/button"
import { ForgotPasswordForm, ForgotPasswordSchema } from "@/lib/types"
import { safeCall } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { Key } from "lucide-react"
import { useForm } from "react-hook-form"
import * as action from "@/lib/services/actions/auth/password-recovery.action"

export default function ForgotPasswordFormComponent() {

    const form = useForm({
        resolver: zodResolver(ForgotPasswordSchema),
        defaultValues: {
            email: ""
        }
    })

    function requestForgotPassword(form : ForgotPasswordForm) {
        safeCall(async () => {
            await action.forgotPasswordRequest(form)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(requestForgotPassword)} className="space-y-4">
            <header>
                <h1 className="text-2xl">Forgot Password</h1>
                <h3 className="text-gray-600">Send OTP Code to your email to reset your password.</h3>
            </header>

            <FormsInput control={form.control} name="email" label="Email" />
 
            <nav className="space-x-2">
                <Button type="submit">
                    <Key /> Forgot Password
                </Button>
            </nav>
        </form>
    )
}
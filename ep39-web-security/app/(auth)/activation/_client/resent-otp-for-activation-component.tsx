'use client'

import { ResendOtpForm, ResendOtpSchema } from "@/lib/types"
import { safeCall } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"

import * as action from "@/lib/services/actions/auth/account-activation.action"
import { FormsInput } from "@/components/forms"
import { Button } from "@/components/ui/button"
import { Send } from "lucide-react"


export default function ResendOtpForActivationComponent() {
    const form = useForm({
        resolver: zodResolver(ResendOtpSchema),
        defaultValues: {
            email: "",
        }
    })

    function resend(form : ResendOtpForm) {
        safeCall(async () => {
            await action.resendOptAction(form)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(resend)} className="space-y-4">
            <header>
                <h1 className="text-2xl">Resend OTP</h1>
                <h3 className="text-gray-600">Resend OTP Code to your email.</h3>
            </header>

            <FormsInput control={form.control} name="email" label="Email" />
 
            <nav className="space-x-2">
                <Button type="submit">
                    <Send /> Resend
                </Button>
            </nav>

        </form>
    )
}
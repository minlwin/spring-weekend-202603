'use client'

import { ResetPasswordForm, ResetPasswordSchema } from "@/lib/types"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import * as action from "@/lib/services/actions/auth/password-recovery.action"
import { safeCall } from "@/lib/utils"
import { Button } from "@/components/ui/button"
import { Key, Send } from "lucide-react"
import Link from "next/link"
import { FormsInput } from "@/components/forms"

export default function ResetPasswordFormComponent({id} : {id : string}) {

    const form = useForm({
        resolver: zodResolver(ResetPasswordSchema),
        defaultValues: {
            password: "",
            securityCode: ""
        }
    })

    function resetPassword(form : ResetPasswordForm) {
        safeCall(async () => {
            await action.resetPassword(id, form)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(resetPassword)} className="space-y-4">
            <header>
                <h1 className="text-2xl">Reset Password</h1>
                <h3 className="text-gray-600">Please check your email and enter OTP Code and New Password.</h3>
            </header>

            <FormsInput control={form.control} name="securityCode" label="OTP Code" />
            <FormsInput control={form.control} name="password" label="Password" type="password" />
 
            <nav className="space-x-2">
                <Button type="submit">
                    <Key /> Reset Password
                </Button>

                <Button render={<Link href={'/password/resend'} />} nativeButton={false} variant={"link"}>
                    <Send /> Resend OTP Code
                </Button>
            </nav>
        </form>
    )
}
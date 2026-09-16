'use client'

import { ActivationForm, ActivationShcma } from "@/lib/types"
import { safeCall } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"

import * as action from "@/lib/services/actions/auth/account-activation.action"
import { FormsInput } from "@/components/forms"
import { Button } from "@/components/ui/button"
import { Key, Send } from "lucide-react"
import Link from "next/link"

export default function ActivationFormComponent({message} : {message? : string}) {

    const form = useForm({
        resolver: zodResolver(ActivationShcma),
        defaultValues: {
            email: "",
            password: "",
            securityCode: ""
        }
    })

    function activate(form : ActivationForm) {
        safeCall(async () => {
            await action.activationAction(form)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(activate)} className="space-y-4">
            <header>
                <h1 className="text-2xl">Activate Account</h1>
                <h3 className="text-gray-600">{message || 'Please check your email and activate your account with OTP Code.'}</h3>
            </header>

            <FormsInput control={form.control} name="email" label="Email" />
            <FormsInput control={form.control} name="securityCode" label="OTP Code" />
            <FormsInput control={form.control} name="password" label="Password" type="password" />
 
            <nav className="space-x-2">
                <Button type="submit">
                    <Key /> Activate
                </Button>

                <Button render={<Link href={'/activation/resend'} />} nativeButton={false} variant={"link"}>
                    <Send /> Resend OTP Code
                </Button>
            </nav>

        </form>
    )
}
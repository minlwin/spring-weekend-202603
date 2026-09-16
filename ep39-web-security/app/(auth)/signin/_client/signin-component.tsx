'use client'

import { FormsInput } from "@/components/forms"
import { Button } from "@/components/ui/button"
import { SignInForm, SignInSchema } from "@/lib/types"
import { zodResolver } from "@hookform/resolvers/zod"
import { Key, Lock, LogIn, UserPlus } from "lucide-react"
import Link from "next/link"
import { useForm } from "react-hook-form"
import * as action from "@/lib/services/actions/auth/security.action"
import { useSearchParams } from "next/navigation"
import { safeCall } from "@/lib/utils"

export default function SignInComponent() {
    
    const searchParams = useSearchParams()
    const message = searchParams.get("message") || "Welcome back."

    const form = useForm<SignInForm>({
        resolver: zodResolver(SignInSchema),
        defaultValues: {
            email: "",
            password: ""
        }
    })

    function signInAction(form : SignInForm) {
        safeCall(async () => {
            await action.signIn(form)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(signInAction)} className="space-y-4">
            <header>
                <h1 className="text-2xl">Sign In</h1>
                <h3 className="text-gray-600">{message}</h3>
            </header>

            <FormsInput control={form.control} name="email" label="Email" type="email" />
            <FormsInput control={form.control} name="password" label="Password" type="password" />

            <nav className="space-x-2">
                <Button type="submit">
                    <LogIn /> Sign In
                </Button>

                <Button render={<Link href={'/signup'} />} nativeButton={false} variant={"outline"}>
                    <UserPlus /> Sign Up
                </Button>
            </nav>

            <div>
                <Button render={<Link href={'/password'} />} nativeButton={false} variant={"link"}>
                    <Lock /> I forgot my password
                </Button>
            </div>
        </form>
    )
}
'use client'

import { FormsInput } from "@/components/forms"
import { Button } from "@/components/ui/button"
import { SignInForm, SignInSchema } from "@/lib/types"
import { zodResolver } from "@hookform/resolvers/zod"
import { LogIn, UserPlus } from "lucide-react"
import Link from "next/link"
import { useForm } from "react-hook-form"
import * as action from "@/lib/services/actions/signin.action"
import { useLoginUserContxt } from "@/lib/state/login-user.context"
import { useRouter, useSearchParams } from "next/navigation"
import { getHome, safeCall } from "@/lib/utils"
import { useEffect } from "react"

export default function SignInPage() {

    const {setLoginUser} = useLoginUserContxt()
    const router = useRouter()
    const searchParams = useSearchParams()
    const message = searchParams.get("message") || "Welcome back."

    const form = useForm<SignInForm>({
        resolver: zodResolver(SignInSchema),
        defaultValues: {
            email: "",
            password: ""
        }
    })

    async function signInAction(form : SignInForm) {
        await safeCall(async () => {
            const loginUser = await action.signIn(form)
            setLoginUser(loginUser)
            router.replace(getHome(loginUser.role))
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
        </form>
    )
}
'use client'

import FormsInput from "@/components/forms/forms-input"
import { Button } from "@/components/ui/button"
import { signInAction } from "@/lib/actions/auth-actions"
import { useLoginUser } from "@/lib/context/login-user-context"
import { SignInForm, SignInSchema } from "@/lib/schema/auth-schema"
import { handle } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { LogIn, UserPlus } from "lucide-react"
import Link from "next/link"
import { useRouter } from "next/navigation"
import { useForm } from "react-hook-form"

export default function SignInFormComponent() {

    const { setUser } = useLoginUser()
    const router = useRouter()

    const form = useForm({
        resolver: zodResolver(SignInSchema),
        defaultValues: {
            email: '',
            password: ''
        }
    })

    async function onSignIn(form: SignInForm) {
        await handle(async () => {
            const result = await signInAction(form)
            setUser(result)
            router.replace(`/${result.role.toLocaleLowerCase()}`)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(onSignIn)} className="space-y-4">
            
            <FormsInput control={form.control} path="email" type="email" label="Email" />
            <FormsInput control={form.control} path="password" type="password" label="Password" />

            <div className="space-x-2">
                <Button type="submit" >
                    <LogIn /> Sign In
                </Button>

                <Button nativeButton={false} render={<Link href='/signup' />} variant={'outline'}>
                    <UserPlus /> Sign Up
                </Button>
            </div>
        </form>
    )
}
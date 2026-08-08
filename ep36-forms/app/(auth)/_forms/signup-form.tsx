'use client'

import FormsInput from "@/components/forms/forms-input"
import { Button } from "@/components/ui/button"
import { signUpAction } from "@/lib/actions/auth-actions"
import { useLoginUser } from "@/lib/context/login-user-context"
import { SignUpForm, SignUpSchema } from "@/lib/schema/auth-schema"
import { handle } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { LogIn, UserPlus } from "lucide-react"
import Link from "next/link"
import { useRouter } from "next/navigation"
import { useForm } from "react-hook-form"

export default function SignUpFormComponent() {

    const { setUser } = useLoginUser()
    const router = useRouter()

    const form = useForm({
        resolver: zodResolver(SignUpSchema),
        defaultValues: {
            email: '',
            password: '',
            name: ''
        }
    })

    async function onSignIn(form: SignUpForm) {
        await handle(async () => {
            const result = await signUpAction(form)
            setUser(result)
            router.replace(`/${result.role.toLocaleLowerCase()}`)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(onSignIn)} className="space-y-4">
            
            <FormsInput control={form.control} path="name" type="text" label="User Name" />
            <FormsInput control={form.control} path="email" type="email" label="Email" />
            <FormsInput control={form.control} path="password" type="password" label="Password" />

            <div className="space-x-2">
                <Button type="submit" >
                    <UserPlus /> Sign Up
                </Button>

                <Button nativeButton={false} render={<Link href='/signin' />} variant={'outline'}>
                    <LogIn /> Sign In
                </Button>
            </div>
        </form>
    )
}
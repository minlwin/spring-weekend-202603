'use client'

import { FormsInput } from "@/components/forms"
import { Button } from "@/components/ui/button"
import { SignUpForm, SignUpSchema } from "@/lib/types"
import { zodResolver } from "@hookform/resolvers/zod"
import { UserPlus } from "lucide-react"
import { useSearchParams } from "next/navigation"
import { useForm } from "react-hook-form"

export default function CandidateSignUpComponent() {

    const searchParams = useSearchParams()
    const message = searchParams.get("message") || "Getting Start your new carrier."

    const form = useForm({
        resolver: zodResolver(SignUpSchema),
        defaultValues: {
            type: 'Candidate',
            name: '',
            email: ''
        }
    })

    function signUp(form: SignUpForm) {

    }

    return (
        <form onSubmit={form.handleSubmit(signUp)} className="space-y-4">

            <header>
                <h1 className="text-2xl">Getting Start</h1>
                <h3 className="text-gray-600">{message}</h3>
            </header>

            <FormsInput control={form.control} name="name" label="Candidate Name" />
            <FormsInput control={form.control} name="email" label="Email" />

            <nav>
                <Button type="submit">
                    <UserPlus /> Sign Up
                </Button>
            </nav>

        </form>
    )
}
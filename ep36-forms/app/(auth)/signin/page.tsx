import { Metadata } from "next";
import SignInForm from "../_forms/signin-form";

export const metadata:Metadata = {
    title: "Sign In | Forms"
}

export default function SignInPage() {
    return (
        <section className="space-y-6">
            <h1 className="text-3xl">Sign In</h1>
            <SignInForm />
        </section>
    )
} 
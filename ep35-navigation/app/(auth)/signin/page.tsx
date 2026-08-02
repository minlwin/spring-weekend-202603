import { Button } from "@/components/ui/button";
import { Field, FieldLabel } from "@/components/ui/field";
import { Input } from "@/components/ui/input";
import { LogInIcon, UserPlus } from "lucide-react";
import Link from "next/link";

export default function SignInPage() {
    return (
        <form>
            <h1 className="text-3xl font-semibold">Sign In</h1>

            <Field className="mb-4 mt-6">
                <FieldLabel>Email</FieldLabel>
                <Input placeholder="Enter Email" type="email" />
            </Field>

            <Field className="mb-4">
                <FieldLabel>Password</FieldLabel>
                <Input placeholder="Enter Password" type="password" />
            </Field>

            <div className="space-x-2">
                <Button>
                    <LogInIcon /> Sign In
                </Button>

                <Button nativeButton={false} render={<Link href="/signup" />} variant={'outline'}>
                    <UserPlus /> Sign Up
                </Button>
            </div>
        </form>
    )
}
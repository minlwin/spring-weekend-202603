import { Button } from "@/components/ui/button";
import { Field, FieldLabel } from "@/components/ui/field";
import { Input } from "@/components/ui/input";
import { LogIn, LogInIcon, UserPlus } from "lucide-react";
import Link from "next/link";

export default function SignUpPage() {
    return (
        <form>
            <h1 className="text-3xl font-semibold">Sign Up</h1>

            <Field className="mb-4 mt-6">
                <FieldLabel>Name</FieldLabel>
                <Input placeholder="Enter Name" type="text" />
            </Field>

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
                    <UserPlus /> Sign Up
                </Button>

                <Button nativeButton={false} variant={'outline'} render={<Link href='/signin' />}>
                    <LogIn /> Sign In
                </Button>
            </div>
        </form>
    )
}
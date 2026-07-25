import { Button } from "@/components/ui/button";
import { Field, FieldLabel } from "@/components/ui/field";
import { Input } from "@/components/ui/input";
import { LogInIcon } from "lucide-react";

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

            <Button>
                <LogInIcon /> Sign In
            </Button>
        </form>
    )
}
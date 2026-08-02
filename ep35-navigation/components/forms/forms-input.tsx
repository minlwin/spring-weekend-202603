import { HTMLInputTypeAttribute } from "react"
import { Field, FieldError, FieldLabel } from "../ui/field"
import { Input } from "../ui/input"

type FormsInputProps = {
    label: string
    name: string
    type?: HTMLInputTypeAttribute
    error?: string
    className?: string
}

export default function FormsInput({label, name, type, error, className} : FormsInputProps) {
    return (
        <Field className={className}>
            <FieldLabel>{label}</FieldLabel>
            <Input name={name} type={type} placeholder={`Please enter ${label}`}/>
            {error && <FieldError>{error}</FieldError>}
        </Field>
    )
}
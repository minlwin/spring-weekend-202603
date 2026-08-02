import { Field, FieldError, FieldLabel } from "../ui/field"
import { Textarea } from "../ui/textarea"

type FormsTextareatProps = {
    label: string
    name: string
    error?: string
    className?: string
}

export default function FormsTextarea({label, name, error, className} : FormsTextareatProps) {
    return (
        <Field className={className}>
            <FieldLabel>{label}</FieldLabel>
            <Textarea name={name} placeholder={`Please enter ${label}`} />
            {error && <FieldError>{error}</FieldError>}
        </Field>
    )
}
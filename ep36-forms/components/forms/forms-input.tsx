import { HTMLInputTypeAttribute } from "react"
import { Control, Controller, FieldValues, Path } from "react-hook-form"
import { Field, FieldError, FieldLabel } from "../ui/field"
import { Input } from "../ui/input"

type FormsInputProps<T extends FieldValues> = {
    control: Control<T>
    path: Path<T>
    label: string
    type?: HTMLInputTypeAttribute
    className?: string
}

export default function FormsInput<T extends FieldValues>({
    control, path, label, type, className
} : FormsInputProps<T>) {
    return (
        <Controller control={control} name={path} render={({field, fieldState}) => 
            <Field className={className} data-invalid={fieldState.invalid}>
                <FieldLabel>{label}</FieldLabel>
                <Input {...field} type={type} placeholder={`Please enter ${label}`} />
                {fieldState.invalid && 
                    <FieldError errors={[fieldState.error]} />
                }
            </Field>
        } />
    )
}
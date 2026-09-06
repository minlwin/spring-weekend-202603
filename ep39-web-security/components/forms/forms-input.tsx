import { HTMLInputTypeAttribute } from "react";
import { Control, Controller, FieldValues, Path } from "react-hook-form";
import { Field, FieldError, FieldLabel } from "@/components/ui/field";
import { Input } from "@/components/ui/input";

type FormsInputProps<T extends FieldValues> = {
    name: Path<T>
    control: Control<T>
    label: string
    type?: HTMLInputTypeAttribute
    placeholder?: string
    className?: string
}

export default function FormsInput<T extends FieldValues>({
    name, control, label, type, placeholder, className
} : FormsInputProps<T>) {
    return (
        <Controller name={name} control={control} render={({field, fieldState}) => 
            <Field data-invalid={fieldState.invalid} className={className}>
                <FieldLabel>{label}</FieldLabel>
                <Input {...field} type={type} 
                    aria-invalid={fieldState.invalid}
                    placeholder={placeholder || `Enter ${label}`} />
                {fieldState.invalid && 
                    <FieldError errors={[fieldState.error]} /> 
                }
            </Field>
        }/>
    )
}
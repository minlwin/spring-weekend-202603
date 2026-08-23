import { Control, Controller, FieldValues, Path } from "react-hook-form";
import { Field, FieldError, FieldLabel } from "../ui/field";
import { Textarea } from "../ui/textarea";

type FormsTextareaProps<T extends FieldValues> = {
    name: Path<T>
    control: Control<T>
    label: string
    placeholder?: string
    className?: string
    rows?: number
    cols?: number
}

export default function FormsTexarea<T extends FieldValues>({
    name, control, label, placeholder, className, rows, cols
} : FormsTextareaProps<T>) {
    return (
        <Controller name={name} control={control} render={({field, fieldState}) => 
            <Field data-invalid={fieldState.invalid} className={className}>
                <FieldLabel>{label}</FieldLabel>
                <Textarea
                    rows={rows} cols={cols}
                    aria-invalid={fieldState.invalid}
                    placeholder={placeholder || `Enter ${label}`} />
                {fieldState.invalid && 
                    <FieldError errors={[fieldState.error]} /> 
                }
            </Field>
        }/>
    )
}
import { Option } from "@/lib/types"
import { Control, Controller, FieldValues, Path } from "react-hook-form"
import { Field, FieldError, FieldLabel } from "../ui/field"
import { NativeSelect, NativeSelectOptGroup, NativeSelectOption } from "../ui/native-select"

type FormsSelectProps<T extends FieldValues> = {
    name: Path<T>
    control: Control<T>
    label: string
    options: Option[]
    className?: string
}

export default function FormsSelect<T extends FieldValues>({
    name, control, label, options, className
} : FormsSelectProps<T>) {
    return (
        <Controller name={name} control={control} render={({field, fieldState}) => 
            <Field data-invalid={fieldState.invalid} className={className}>
                <FieldLabel>{label}</FieldLabel>

                <NativeSelect {...field} 
                    aria-invalid={fieldState.invalid}>
                    {options.map((item, index) => 
                        <NativeSelectOption key={index} value={item.value}>{item.label}</NativeSelectOption>
                    )}
                </NativeSelect>

                {fieldState.invalid && 
                    <FieldError errors={[fieldState.error]} /> 
                }
            </Field>
        }/>
    )
}
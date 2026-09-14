import z from "zod"

export type ActivationStatus = "" | "true" | "false"

export type EmployeeSearch = {
    activated : ActivationStatus
    keyword : string
}

export type EmployeeListItem = {
    id: number
    name: string
    email: string
    phone: string
    activatedAt: string
}

type EntityLog = {
    createdBy: string
    modifiedBy: string
    createdAt: string
    modifiedAt: string
}

export type EmployeeDetails = EmployeeListItem & {
    retiredAt: string
} & EntityLog

export const EmployeeSchema = z.object({
    name : z.string().nonempty("Please enter employee name."),
    email : z.string().nonempty("Please enter email address.").email("Please enter valid email."),
    phone: z.string().nonempty("Please enter phone number.")
})

export type EmployeeForm = z.infer<typeof EmployeeSchema>
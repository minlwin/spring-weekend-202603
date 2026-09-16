'use client'

import { EmployeeForm, EmployeeSchema } from "@/lib/types";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import * as action from '@/lib/services/actions/management/employee-management.action'
import { safeCall } from "@/lib/utils";
import PageTemplate from "@/components/commons/page-template";
import { FormsInput } from "@/components/forms";
import { Button } from "@/components/ui/button";
import { Save } from "lucide-react";

export default function EmployeeEditForm({id, value} : {id? : any, value?: EmployeeForm}) {
    const form = useForm({
        resolver: zodResolver(EmployeeSchema),
        defaultValues: value || {
            name: "",
            phone: "",
            email: ""
        }
    })

    function save(formData : EmployeeForm) {
        safeCall(async () => {
            if(id) {
                await action.update(id, formData)
            } else {
                await action.create(formData)
            }
        })    
    }

    return (
        <PageTemplate title={id ? "Edit Employee" : "Add New Employee"}>
            <form onSubmit={form.handleSubmit(save)} className="w-1/3 space-y-4">
                <FormsInput control={form.control} name="name" label="Name" />
                <FormsInput control={form.control} name="phone" label="Phone" type="tel" />
                <FormsInput control={form.control} name="email" label="Email" type="email" />

                <Button type="submit">
                    <Save /> Save Employee
                </Button>
            </form>
        </PageTemplate>
    )
}
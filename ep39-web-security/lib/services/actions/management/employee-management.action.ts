'use server'

import { EmployeeForm, EmployeeSearch } from "@/lib/types";
import * as client from '@/lib/services/clients/management/employee-management.client'
import { redirect } from "next/navigation";

export async function search(form : EmployeeSearch) {
    return await client.search(form)
}

export async function findById(id : any) {
    return await client.findById(id)
}

export async function create(form : EmployeeForm) {
    const result = await client.create(form)
    redirect(`/management/employees/${result.id}`)
}

export async function update(id : any, form : EmployeeForm) {
    const result = await client.update(id, form)
    redirect(`/management/employees/${result.id}`)
}
import { EmployeeDetails, EmployeeForm, EmployeeListItem, EmployeeSearch, ModificationResult } from "@/lib/types";
import "server-only"
import { securedRequest } from "..";

const ENDPOINT = 'management/employees'

export async function search(form : EmployeeSearch):Promise<EmployeeListItem[]> {
    return await securedRequest({
        path: ENDPOINT,
        method: 'get',
        params: form
    })
}

export async function findById(id : any):Promise<EmployeeDetails> {
    return await securedRequest({
        path: `${ENDPOINT}/${id}`,
        method: 'get'
    })
}

export async function create(form : EmployeeForm) : Promise<ModificationResult<number>> {
    return await securedRequest({
        path: ENDPOINT,
        method: 'post',
        params: form
    }) 
}

export async function update(id: any, form : EmployeeForm) : Promise<ModificationResult<number>> {
    return await securedRequest({
        path: `${ENDPOINT}/${id}`,
        method: 'put',
        params: form
    }) 
}
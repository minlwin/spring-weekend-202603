'use server'

import { CourseForm } from "../models/course-models"
import * as courseModel from '@/lib/models/course-models'

export type Errors = {
    [key:string] : string
}

export type ActionState = {
    finish: boolean,
    errors?: Errors
}

export async function createCourse(state: ActionState, form: FormData):Promise<ActionState> {

    // Extract data from Form
    const data:CourseForm = {
        name: getString(form, 'name'),
        hours: getNumber(form, 'hours'),
        fees: getNumber(form, 'fees'),
        description: getString(form, 'description')
    }

    // Validate User Inputs
    const errors:Errors = {}

    if(!data.name) {
        errors['name'] = "Please enter course name."
    }

    if(!data.hours) {
        errors['hours'] = "Please enter course hours."
    }

    if(!data.fees) {
        errors['fees'] = "Please enter course fees."
    }

    if(!data.description) {
        errors['description'] = "Please enter course description."
    }

    if(Object.keys(errors).length == 0) {
        // Create Course
        await courseModel.create(data)
    }

    return {
        finish: true,
        errors: Object.keys(errors).length > 0 ? errors : undefined
    }
}

function getString(form:FormData, key: string) : string {
    return form.get(key)?.toString() ?? ""
}

function getNumber(form:FormData, key: string) : number {
    const strValue = getString(form, key)

    if(strValue) {
        return Number.parseInt(strValue)
    }

    return 0
}

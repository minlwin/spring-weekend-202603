'use server'

import * as userModel from '@/lib/model/users-model'
import { SignInForm, SignUpForm } from '../schema/auth-schema'

export async function signInAction(form: SignInForm) {
    return await userModel.login(form.email, form.password)
}

export async function signUpAction(form: SignUpForm) {
    return await userModel.create({...form, role: 'Student'})
}
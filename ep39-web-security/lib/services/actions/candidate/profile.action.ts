'use server'
import * as client from '@/lib/services/clients/candidate/personal-info.client'
import { PersonalInfoForm } from '@/lib/types'
import { redirect } from 'next/navigation'

export async function getProfile(email : string) {
    return await client.getPersonalInfo(email)
}

export async function update(id: any, form : PersonalInfoForm) {
    await client.update(id, form)
    redirect('/candidate/personal-info')
}

export async function uploadPhoto(id: any, file: any) {
    await client.uploadPhoto(id, file)
    redirect('/candidate/personal-info')
}

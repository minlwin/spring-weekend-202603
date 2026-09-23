import { ModificationResult, PersonalInfoForm, PersonalInformation } from '@/lib/types';
import 'server-only'
import { securedRequest } from '..';

const ENDPOINT = "candidate/personal-info"

export async function getPersonalInfo(email: string) : Promise<PersonalInformation> {
    return securedRequest({
        path: `${ENDPOINT}/${email}`,
        method: 'get'
    });
}

export async function update(id : any, form : PersonalInfoForm) : Promise<ModificationResult<number>> {
    return securedRequest({
        path: `${ENDPOINT}/${id}`,
        method: 'put',
        params: form
    })
}

export async function uploadPhoto(id : any, file : any) : Promise<ModificationResult<number>> {
    return securedRequest({
        path: `${ENDPOINT}/${id}/photo`,
        method: 'put',
        useFile: true,
        params: {
            file: file
        }
    })
}

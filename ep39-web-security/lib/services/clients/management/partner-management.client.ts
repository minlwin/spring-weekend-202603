import { PartnerListItem, PartnerSearch } from '@/lib/types/management/partner-management';
import 'server-only'
import { securedRequest } from '..';

const ENDPOINT = "management/partners"

export async function search(form?: PartnerSearch):Promise<PartnerListItem[]> {
    return await securedRequest({
        path: ENDPOINT,
        method: 'get',
        params: form
    })
}
'use server'

import { PartnerSearch } from "@/lib/types/management/partner-management";
import * as client from "@/lib/services/clients/management/partner-management.client"

export async function search(form? : PartnerSearch) {
    return await client.search(form)
}
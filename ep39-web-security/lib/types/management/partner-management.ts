export type PartnerSearch = {
    status : 'true' | 'false' | ''
    registerFrom : string
    registerTo : string
    keyword : string
}

export type PartnerListItem = {
    id: number
    name: string
    registerdAt: string
    activatedAt: string
    company: string
    email: string
    phone: string
}
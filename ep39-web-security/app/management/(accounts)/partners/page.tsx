'use client'

import NoData from "@/components/commons/nodata"
import PageTemplate from "@/components/commons/page-template"
import { FormsInput, FormsSelect } from "@/components/forms"
import { Button } from "@/components/ui/button"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { PartnerListItem, PartnerSearch } from "@/lib/types/management/partner-management"
import { formatDateTime, safeCall } from "@/lib/utils"
import { Search } from "lucide-react"
import { useEffect, useState } from "react"
import { useForm } from "react-hook-form"

import * as action from "@/lib/services/actions/management/partner-management.action"

export default function PartnerManagementPage() {

    const [list, setList] = useState<PartnerListItem[]>([])

    function search(form?: PartnerSearch) {
        safeCall(async () => {
            const result = await action.search(form)
            setList(result)
        })
    }

    useEffect(() => {
        search()
    }, [setList])

    return (
        <PageTemplate title="Partner Management">
            <PartnerSearchForm onSearch={search} />
            <PartnerResultList list={list} />
        </PageTemplate>
    )
}

function PartnerResultList({list} : {list : PartnerListItem[]}) {

    if(list.length == 0) {
        return (
            <NoData name="Partner Informations" /> 
        )
    }

    return (
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead>Name</TableHead>
                    <TableHead>Email</TableHead>
                    <TableHead>Company</TableHead>
                    <TableHead>Phone</TableHead>
                    <TableHead>Registered At</TableHead>
                    <TableHead>Activated At</TableHead>
                </TableRow>
            </TableHeader>

            <TableBody>
                {list.map(item => 
                    <TableRow key={item.id}>
                        <TableCell>{item.name}</TableCell>
                        <TableCell>{item.email}</TableCell>
                        <TableCell>{item.company || 'Not Define'}</TableCell>
                        <TableCell>{item.phone || 'Not Define'}</TableCell>
                        <TableCell>{formatDateTime(item.registerdAt)}</TableCell>
                        <TableCell>{formatDateTime(item.activatedAt) || 'Pending'}</TableCell>
                    </TableRow>
                )}
            </TableBody>
        </Table>
    )
}

function PartnerSearchForm({onSearch} : {onSearch : (form : PartnerSearch) => void}) {

    const form = useForm<PartnerSearch>({defaultValues: {
        status: '',
        registerFrom: '',
        registerTo: '',
        keyword: ''
    }})

    return (
        <form onSubmit={form.handleSubmit(onSearch)} className="flex gap-4 items-end">
            <FormsSelect control={form.control} name="status" label="Status" className="w-auto" options={[
                {label : 'Search All', value: ''},
                {label : 'Activated', value: 'true'},
                {label : 'Pending', value: 'false'},
            ]} />

            <FormsInput control={form.control} name="registerFrom" label="Register From" type="date" className="w-auto" />
            <FormsInput control={form.control} name="registerTo" label="Register To" type="date" className="w-auto" />
            <FormsInput control={form.control} name="keyword" label="Keyword" className="w-auto" />

            <nav>
                <Button type="submit">
                    <Search /> Search
                </Button>
            </nav>
        </form>
    )
}

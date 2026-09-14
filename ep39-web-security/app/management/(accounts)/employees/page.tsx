'use client'

import PageTemplate from "@/components/commons/page-template";
import { FormsInput, FormsSelect } from "@/components/forms";
import { Button } from "@/components/ui/button";
import { EmployeeListItem, EmployeeSearch } from "@/lib/types";
import { ArrowRight, Search, UserPlus } from "lucide-react";
import Link from "next/link";
import { useState } from "react";
import { useForm } from "react-hook-form";
import * as action from "@/lib/services/actions/management/employee-management.action"
import { safeCall } from "@/lib/utils";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import NoData from "@/components/commons/nodata";

export default function EmployeeManagementPage() {

    const [list, setList] = useState<EmployeeListItem[]>([])

    function search(form : EmployeeSearch) {
        safeCall(async () => {
            const result = await action.search(form)
            setList(result)
        })
    }

    return (
        <PageTemplate title="Search Employee">
            <SearchForm onSearch={search} />
            <TableView list={list} />
        </PageTemplate>
    )
}

function TableView({list} : {list : EmployeeListItem[]}) {

    if(!list.length) {
        return (
            <NoData name="Employee" />
        )
    }

    return (
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead>Name</TableHead>
                    <TableHead>Phone</TableHead>
                    <TableHead>Email</TableHead>
                    <TableHead>Activated At</TableHead>
                    <TableHead></TableHead>
                </TableRow>
            </TableHeader>

            <TableBody>
            {list.map(item => 
                <TableRow key={item.id}>
                    <TableCell>{item.name}</TableCell>
                    <TableCell>{item.phone}</TableCell>
                    <TableCell>{item.email}</TableCell>
                    <TableCell>{item.activatedAt}</TableCell>
                    <TableCell>
                        <Link href={`/management/employees/${item.id}`}>
                            <ArrowRight size={16} />
                        </Link>
                    </TableCell>
                </TableRow>
            )}
            </TableBody>
        </Table>
    )
}

function SearchForm({onSearch} : {onSearch : (form: EmployeeSearch) => void}) {

    const form = useForm<EmployeeSearch>({defaultValues : {
        activated: '',
        keyword: ''
    }})

    return (
        <form onSubmit={form.handleSubmit(onSearch)} className="flex items-end gap-4">
            <FormsSelect control={form.control} name="activated" label="Status" className="w-fit" options={[
                {label : "All Status", value : ""},
                {label : "Activated", value : "true"},
                {label : "Pending", value : "false"},
            ]} />

            <FormsInput control={form.control} name="keyword" label="Keyword" className="w-fit" />

            <div className="space-x-2">
                <Button type="submit">
                    <Search /> Search
                </Button>

                <Button type="button" render={<Link href={'/management/employees/edit'} />} nativeButton={false} variant={'destructive'}>
                    <UserPlus /> Add Employee
                </Button>
            </div>
        </form>
    )
}
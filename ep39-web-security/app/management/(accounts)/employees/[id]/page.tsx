import AuditInfo from '@/components/commons/audit-info'
import PageTemplate from '@/components/commons/page-template'
import { Avatar, AvatarFallback } from '@/components/ui/avatar'
import { Button } from '@/components/ui/button'
import { Item, ItemContent, ItemDescription, ItemMedia, ItemTitle } from '@/components/ui/item'
import * as action from '@/lib/services/actions/management/employee-management.action'
import { formatDateTime } from '@/lib/utils'
import { Pencil, User, UserCheck, UserX } from 'lucide-react'
import Link from 'next/link'

export default async function EmployeeDetailsPage({ params } : PageProps<'/management/employees/[id]'>) {

    const { id } = await params 
    const employee = await action.findById(id)

    return (
        <PageTemplate title='Employee Information'>
            <section className='space-y-6'>
                <div className='flex items-center gap-4'>
                    <Avatar size='lg'>
                        <AvatarFallback>
                            <User />
                        </AvatarFallback>
                    </Avatar> 

                    <div>
                        <span className='text-xl font-semibold'>
                            {employee.name}
                        </span>

                        <div>
                            {employee.phone} | {employee.email}
                        </div>
                    </div>
                </div>

                <div className='space-y-4'>
                    <h3 className='text-gray-500 font-semibold'>Status</h3>

                    <div className='grid grid-cols-5 gap-4'>
                        <Item variant={'outline'}>
                            <ItemMedia>
                                <UserCheck />
                            </ItemMedia>
                            <ItemContent>
                                <ItemTitle>Activated At</ItemTitle>
                                <ItemDescription>{formatDateTime(employee.activatedAt) || 'Pending'}</ItemDescription>
                            </ItemContent>
                        </Item>

                        {employee.retiredAt && 
                            <Item variant={'outline'}>
                                <ItemMedia>
                                    <UserX />
                                </ItemMedia>

                                <ItemContent>
                                    <ItemTitle>Retired At</ItemTitle>
                                    <ItemDescription>{formatDateTime(employee.retiredAt)}</ItemDescription>
                                </ItemContent>
                            </Item>
                        }
                    </div>
                </div>

                <AuditInfo createdAt={employee.createdAt} createdBy={employee.createdBy} modifiedAt={employee.modifiedAt} modifiedBy={employee.modifiedBy} />

                <nav>
                    <Button nativeButton={false} render={<Link href={`/management/employees/edit/${employee.id}`} />}>
                        <Pencil /> Edit Employee
                    </Button>
                </nav>
            </section>
        </PageTemplate>
    )
}
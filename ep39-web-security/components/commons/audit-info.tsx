import { Calendar, UserPen, UserPlus } from "lucide-react"
import { Item, ItemContent, ItemDescription, ItemMedia, ItemTitle } from "../ui/item"
import { formatDateTime } from "@/lib/utils"

type AuditInfoProps = {
    createdBy: string
    createdAt: string
    modifiedBy: string
    modifiedAt: string
}

export default function AuditInfo({createdAt, createdBy, modifiedAt, modifiedBy} : AuditInfoProps) {
    return (
        <section className="space-y-4">
            <h3 className='text-gray-500 font-semibold'>Audit Information</h3>
            <div className='grid grid-cols-5 gap-4'>
                <Item variant={'outline'}>
                    <ItemMedia>
                        <UserPlus />
                    </ItemMedia>
                    <ItemContent>
                        <ItemTitle>Created By</ItemTitle>
                        <ItemDescription>{createdBy === 'anonymousUser' ? 'Anonymous User' : createdBy}</ItemDescription>
                    </ItemContent>
                </Item>

                <Item variant={'outline'}>
                    <ItemMedia>
                        <Calendar />
                    </ItemMedia>
                    <ItemContent>
                        <ItemTitle>Created At</ItemTitle>
                        <ItemDescription>{formatDateTime(createdAt)}</ItemDescription>
                    </ItemContent>
                </Item>

                <Item variant={'outline'}>
                    <ItemMedia>
                        <UserPen />
                    </ItemMedia>
                    <ItemContent>
                        <ItemTitle>Last Modified By</ItemTitle>
                        <ItemDescription>{modifiedBy  === 'anonymousUser' ? 'Anonymous User' : modifiedBy}</ItemDescription>
                    </ItemContent>
                </Item>

                <Item variant={'outline'}>
                    <ItemMedia>
                        <Calendar />
                    </ItemMedia>
                    <ItemContent>
                        <ItemTitle>Last Modified At</ItemTitle>
                        <ItemDescription>{formatDateTime(modifiedAt)}</ItemDescription>
                    </ItemContent>
                </Item>

            </div>
        </section>
    )
}
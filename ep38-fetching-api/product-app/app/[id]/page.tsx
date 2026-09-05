import { Button } from "@/components/ui/button"
import { Separator } from "@/components/ui/separator"
import * as action from "@/lib/model/action/product-action"
import { cn } from "@/lib/utils"
import { ArrowLeft, Calendar, CalendarClock, SquarePen, Tag } from "lucide-react"
import Link from "next/link"

type Props = {
    params: Promise<{ id: string }>
}

export default async function ProductDetailsPage({ params }: Props) {
    const { id } = await params
    const product = await action.findById(Number(id))

    return (
        <section className="space-y-4">
            <div className="flex items-center justify-between">
                <Button variant={'outline'} size={'sm'} nativeButton={false}
                    render={<Link href={'/'} />}>
                    <ArrowLeft /> Back to Products
                </Button>

                <Button nativeButton={false}
                    render={<Link href={`/${product.id}/edit`} />}>
                    <SquarePen /> Edit Product
                </Button>
            </div>

            <div className="rounded-3xl border border-border bg-card text-card-foreground overflow-hidden">
                <div className="p-6 space-y-3">
                    <div className="flex items-start justify-between gap-4">
                        <div className="space-y-1">
                            <p className="text-sm text-muted-foreground">#{product.id}</p>
                            <h1 className="text-2xl font-semibold">{product.name}</h1>
                        </div>

                        <span className={cn(
                            "shrink-0 rounded-full px-3 py-1 text-xs font-medium",
                            product.status === "Available"
                                ? "bg-primary/10 text-primary"
                                : "bg-destructive/10 text-destructive"
                        )}>
                            {product.status}
                        </span>
                    </div>

                    <div className="flex items-center gap-2 text-sm text-muted-foreground">
                        <Tag size={14} />
                        {product.category}
                    </div>
                </div>

                <Separator />

                <div className="p-6 grid grid-cols-1 sm:grid-cols-3 gap-6">
                    <div>
                        <p className="text-sm text-muted-foreground">Unit Price</p>
                        <p className="text-lg font-semibold">{product.unitPrice.toLocaleString()} MMK</p>
                    </div>

                    <div className="sm:col-span-2">
                        <p className="text-sm text-muted-foreground">Description</p>
                        <p className={cn(!product.description && "italic text-muted-foreground")}>
                            {product.description || "No description provided."}
                        </p>
                    </div>
                </div>

                <Separator />

                <div className="p-6 flex flex-wrap gap-x-8 gap-y-2 text-sm text-muted-foreground">
                    <div className="flex items-center gap-2">
                        <Calendar size={14} />
                        Created {product.createdAt}
                    </div>

                    <div className="flex items-center gap-2">
                        <CalendarClock size={14} />
                        Last Modified {product.modifiedAt}
                    </div>
                </div>
            </div>
        </section>
    )
}

'use client'

import FormsInput from "@/components/forms/forms-input"
import FormsSelect from "@/components/forms/forms-select"
import FormsTexarea from "@/components/forms/forms-textarea"
import { Button } from "@/components/ui/button"
import { ProductForm, ProductSchema } from "@/lib/types"
import { zodResolver } from "@hookform/resolvers/zod"
import { RefreshCcw, Save } from "lucide-react"
import { useForm } from "react-hook-form"

export default function AddNewPage() {

    const form = useForm<ProductForm>({
        resolver: zodResolver(ProductSchema),
        defaultValues: {
            name: "",
            category: "",
            status: "",
            description: "",
            price: 0
        }
    })

    function onSave(form: ProductForm) {

    }

    return (
        <section className="space-y-4">
            <h1 className="text-2xl">Create New Product</h1>

            <form onSubmit={form.handleSubmit(onSave)} className="grid grid-cols-4 gap-4">
                <FormsInput control={form.control} name="name" label="Name" className="col-span-2" />
                <FormsInput control={form.control} name="category" label="Category" className="col-start-1" />
                <FormsInput control={form.control} name="price" type="number" label="Price" />
                <FormsSelect control={form.control} name="status" label="Status" options={[
                    {value : "", label: "Select One"},
                    {value : "Available", label: "Available"},
                    {value : "Pending", label: "Pending"},
                ]} />

                <FormsTexarea control={form.control} name="description" label="Description" className="col-start-1 col-span-3" />

                <div className="col-start-1 col-span-3 space-x-1">
                    <Button type="reset" variant={'outline'}>
                        <RefreshCcw /> Reset
                    </Button>
                    <Button type="submit">
                        <Save /> Save Product
                    </Button>
                </div>
            </form>
        </section>
    )
}
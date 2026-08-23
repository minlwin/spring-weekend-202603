import z from "zod";

export type ProductStatus = 'Available' | 'Pending'

export const ProductSearchSchema = z.object({
    status: z.enum(['', 'Available', 'Pending']),
    keyword: z.string()
})

export type ProductSearch = z.infer<typeof ProductSearchSchema>

export type Option = {
    value: string
    label: string
}

export type ProductListItem = {
    id: number
    name: string
    category: string
    price: number
    status: ProductStatus
}

export const ProductSchema = z.object({
    name: z.string().nonempty('Enter Product Name.'),
    category: z.string().nonempty("Enter Category."),
    description: z.string().nullable().optional(),
    status: z.string().nonempty("Select Status."),
    price: z.number().nullable().optional().refine(
        value => value !== null && value !== undefined && value > 0, {
            message: "Please enter a valid price value."
        }
    ),
})

export type ProductForm = z.infer<typeof ProductSchema>

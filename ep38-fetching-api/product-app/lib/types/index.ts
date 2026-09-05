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
    unitPrice: number
    status: ProductStatus
}

export const ProductSchema = z.object({
    name: z.string().nonempty('Enter Product Name.'),
    category: z.string().nonempty("Enter Category."),
    description: z.string().nullable().optional(),
    status: z.string().nonempty("Select Status."),
    unitPrice: z.string().nonempty("Please enter Price."),
})

export type ProductForm = z.infer<typeof ProductSchema>

export type ProductDetails = ProductListItem & {
    description?: string
    createdAt?: string
    modifiedAt?: string
}

export type ModificationResult<T> = {
    id: T
}
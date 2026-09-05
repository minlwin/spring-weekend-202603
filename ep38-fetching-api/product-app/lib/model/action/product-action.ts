'use server'
import * as client from '@/lib/model/client/product-client'

import { ModificationResult, ProductDetails, ProductForm, ProductListItem, ProductSearch } from "@/lib/types";

export async function search(form: ProductSearch) : Promise<ProductListItem[]>{
    return await client.search(form)
}

export async function findById(id: any) : Promise<ProductDetails> {
    return await client.findById(id)
}

export async function create(form: ProductForm) : Promise<ModificationResult<number>> {
    return await client.create(form)
}

export async function update(id: any ,form: ProductForm) : Promise<ModificationResult<number>> {
    return await client.update(id, form)
}

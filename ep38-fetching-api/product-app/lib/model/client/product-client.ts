import 'server-only'
import {url} from '@/lib/utils'

import { ModificationResult, ProductDetails, ProductForm, ProductListItem, ProductSearch } from "@/lib/types";

const PATH = 'products'

export async function search(form: ProductSearch) : Promise<ProductListItem[]>{
    const response = await fetch(url(PATH, form))
    return await response.json()
}

export async function findById(id: number) : Promise<ProductDetails> {
    const response = await fetch(url(`${PATH}/${id}`))
    return await response.json()
}

export async function create(form: ProductForm) : Promise<ModificationResult<number>> {
    const response = await fetch(url(PATH), {
        method: "post",
        body: JSON.stringify(form),
        headers: {
            'Content-Type' : 'application/json'
        }
    })
    return await response.json()
}

export async function update(id: number ,form: ProductForm) : Promise<ModificationResult<number>> {
    const response = await fetch(url(`${PATH}/${id}`), {
        method: "put",
        body: JSON.stringify(form),
        headers: {
            'Content-Type' : 'application/json'
        }
    })
    return await response.json()
}
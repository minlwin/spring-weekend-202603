'use client'

import FormsInput from "@/components/forms/forms-input"
import FormsSelect from "@/components/forms/forms-select"
import { Button } from "@/components/ui/button"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { ProductListItem, ProductSearch, ProductSearchSchema } from "@/lib/types"
import { zodResolver } from "@hookform/resolvers/zod"
import { Plus, Search } from "lucide-react"
import Link from "next/link"
import { useState } from "react"
import { useForm } from "react-hook-form"

export default function ProductListPage() {

  const [list, setList] = useState<ProductListItem[]>([])

  const form = useForm<ProductSearch>({
    resolver: zodResolver(ProductSearchSchema),
    defaultValues: {
      status: "",
      "keyword" : ""
    }
  })

  function onSearch(form: ProductSearch) {

  }

  return (
    <section className="space-y-4">
      {/* Search Form */}
      <form onSubmit={form.handleSubmit(onSearch)} className="flex items-end gap-4">
        <FormsSelect control={form.control} name="status" label="Status" className="w-1/6"
          options={[
            {value : "", label : "All Status"},
            {value: "Available", label: "Available"},
            {value: "Pending", label: "Pending"},
          ]} />

        <FormsInput control={form.control} name="keyword" label="Search Keyword" className="w-1/4" />

        <div className="space-x-1">
          <Button type="submit">
            <Search /> Search
          </Button>

          <Button type="button" variant={"destructive"} nativeButton={false}
            render={<Link href={'/edit'} />}>
            <Plus /> Add New
          </Button>
        </div>
      </form>

      {/* Result Table */}
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead>ID</TableHead>
            <TableHead>Category</TableHead>
            <TableHead>Name</TableHead>
            <TableHead>Unit Price</TableHead>
            <TableHead>Status</TableHead>
          </TableRow>
        </TableHeader>

        <TableBody>
          {list.map(item => 
            <TableRow key={item.id}>
              <TableCell>{item.id}</TableCell>
              <TableCell>{item.category}</TableCell>
              <TableCell>{item.name}</TableCell>
              <TableCell>{item.price.toLocaleString()} MMK</TableCell>
              <TableCell>{item.status}</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </section>
  )
}
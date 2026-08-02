import { Card, CardContent, CardDescription, CardTitle } from "@/components/ui/card";
import PageTitle from "../_commons/page-title";
import { ArrowRight, BookOpenCheck, Plus } from "lucide-react";
import { Button } from "@/components/ui/button";

import * as model from '@/lib/models/course-models'
import NoData from "@/components/application/no-data";
import Link from "next/link";

export default async function CourseListPage() {

    const list = await model.getAll()

    return (
        <div>
            <PageTitle name="Our Course"  action={
                <Button nativeButton={false} render={<Link href={'/courses/edit'} />}>
                    <Plus /> Add New
                </Button>
            }/>

            <div className="mt-4">
                {list.length > 0 ?
                    <section className="grid grid-cols-2 gap-4">
                        {list.map(item =>
                            <Card key={item.id}>
                                <CardContent className="flex gap-4">
                                    <div className="px-8">
                                        <BookOpenCheck size={120} />
                                    </div>
                                    <div>
                                        <CardTitle className="text-2xl">{item.name}</CardTitle>
                                        <CardDescription>{item.description}</CardDescription>

                                        <div className="mt-4">
                                            <Button nativeButton={false} render={<Link href={`/courses/${item.id}`} />}>
                                                <ArrowRight /> Read More
                                            </Button>
                                        </div>
                                    </div>
                                </CardContent>
                            </Card>
                        )}
                    </section>  
                    :  
                    <NoData name="Course" />        
                }                
            </div>
        </div>
    )
}
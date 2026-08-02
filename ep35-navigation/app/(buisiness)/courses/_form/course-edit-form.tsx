'use client'

import FormsInput from "@/components/forms/forms-input";
import FormsTextarea from "@/components/forms/forms-textarea";
import { Button } from "@/components/ui/button";
import { createCourse } from "@/lib/actions/course-actions";
import { Loader, Save } from "lucide-react";
import { useRouter } from "next/navigation";
import { useActionState, useEffect } from "react";

export default function CourseEditForm() {
    const router = useRouter()
    const [state, action, pending] = useActionState(createCourse, {finish : false})

    useEffect(() => {
        if(state.finish && !state.errors) {
            router.replace("/courses")
        }
    }, [state])

    return (
        <form action={action}>
            <FormsInput label="Course Name" name="name" className="mb-4" 
                error={state.errors?.name}  />

            <div className="grid grid-cols-2 gap-4 mb-4">
                <FormsInput label="Hours" name="hours" type="number" error={state.errors?.hours} />
                <FormsInput label="Fee" name="fees" type="number" error={state.errors?.fees}/>
            </div>

            <FormsTextarea label="Description" name="description" className="mb-4" error={state.errors?.description} />

            <div>
                <Button type="submit">
                    {pending ? 
                        <Loader /> : 
                        <Save />
                    } Save Course
                </Button>
            </div>
        </form>
    )
}
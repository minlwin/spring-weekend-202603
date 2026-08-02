import * as courseModel from '@/lib/models/course-models'
import PageTitle from '../../_commons/page-title'

export default async function CourseDetailsPage({params} : {params : Promise<{id: string}>}) {

    const { id } = await params
    const course = await courseModel.findById(Number.parseInt(id))

    if(!course) {
        throw new Error("There is no course")
    }

    return (
        <main>
            <PageTitle name='Course Details' />

            <pre>{JSON.stringify(course, null, 2)}</pre>
        </main>
    )
}
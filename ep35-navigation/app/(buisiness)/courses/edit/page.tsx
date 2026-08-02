import PageTitle from "../../_commons/page-title";
import CourseEditForm from "../_form/course-edit-form";

export default function CourseEditPage() {
    return (
        <main>
            <PageTitle name="Add New Course" />

            <section className="w-1/3 mt-4">
                <CourseEditForm />
            </section>
        </main>
    )
}
import { getLoginUser } from "@/lib/services/storage/security-context"
import { redirect } from "next/navigation"
import * as actions from "@/lib/services/actions/candidate/profile.action"
import EditPersonalInfoComponent from "../_client/edit-personal-info"
import PageTemplate from "@/components/commons/page-template"

export default async function EditPersonalInfoPage() {

    const loginUser = await getLoginUser()

    if(!loginUser) {
        // Redirect to Sign In Page
        redirect("/signin?message=You have to login for this operation.")
    }

    const personalInfo = await actions.getProfile(loginUser.email)

    return (
        <PageTemplate title="Edit Personal Information">
            <EditPersonalInfoComponent info={personalInfo} />
        </PageTemplate>
    )
}
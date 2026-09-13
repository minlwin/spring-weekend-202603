import PageHeader from "@/components/commons/page-header";
import ManagementSideBar from "@/components/sidebar/management-sidebar";
import { SidebarProvider, SidebarTrigger } from "@/components/ui/sidebar";
import { getLoginUser } from "@/lib/services/security/security-context";
import { LayoutsProps } from "@/lib/types";
import { getHome } from "@/lib/utils";
import { redirect } from "next/navigation";

export default async function ManagementLayout({children} : LayoutsProps) {
    const loginUser = await getLoginUser()

    if(!loginUser) {
        // Redirect to Sign In Page
        redirect("/signin?message=You have to login for this operation.")
    }

    if(!['Admin', 'Employee'].includes(loginUser.role)) {
        redirect(`${getHome(loginUser.role)}?message=You have no authority for this operation.`)
    }

    return (
        <SidebarProvider className="space-x-4 pr-4">
            <ManagementSideBar />

            <main className="space-y-4 w-full">
                <PageHeader title={'Management Portal'} />
                <section>
                    {children}
                </section>
            </main>
        </SidebarProvider>
    )
}
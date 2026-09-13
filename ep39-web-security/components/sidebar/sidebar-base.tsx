import { Briefcase } from "lucide-react"
import { Sidebar, SidebarContent, SidebarHeader } from "../ui/sidebar"
import SidebarBaseFooter from "./sidebar-base-footer"
import { getLoginUser } from "@/lib/services/security/security-context"

type SidebarProps = {
    title: string
}

export default async function SidebarBase({
    title
} : SidebarProps) {
    const loginUser = await getLoginUser()
    return (
        <Sidebar>
            <SidebarHeader>
                <div className="flex gap-2">
                    <div className="flex items-center justify-center">
                        <div className="bg-black rounded-xl p-2">
                            <Briefcase color="#ffffff" />
                        </div>
                    </div>

                    <div>
                        <h1 className="text-xl font-semibold">Hello Job</h1>
                        <h3 className="font-semibold text-gray-500 leading-none">{title}</h3>
                    </div>
                </div>
            </SidebarHeader>

            <SidebarContent>

            </SidebarContent>

            <SidebarBaseFooter loginUser={loginUser} />
        </Sidebar>
    )
}
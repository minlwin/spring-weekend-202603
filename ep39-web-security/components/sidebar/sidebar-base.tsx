import { Briefcase } from "lucide-react"
import { Sidebar, SidebarContent, SidebarGroup, SidebarGroupContent, SidebarGroupLabel, SidebarHeader, SidebarMenu, SidebarMenuButton, SidebarMenuItem } from "../ui/sidebar"
import SidebarBaseFooter from "./sidebar-base-footer"
import { getLoginUser } from "@/lib/services/storage/security-context"
import React from "react"
import SidebarMenuLink from "./sidebar-menu-link"

export type SidebarMenuItem = {
    name: string
    icon: React.ReactNode,
    link: string    
    feature?: string
}

export type SidebarMenuGroup = {
    name?: string
    items: SidebarMenuItem[]
}

type SidebarProps = {
    title: string,
    menus: SidebarMenuGroup[]
}

export default async function SidebarBase({
    title,
    menus
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
            {menus.map((group, index) => (
                <SidebarGroup key={`G-${index}`}>
                    {group.name && 
                        <SidebarGroupLabel>{group.name}</SidebarGroupLabel>
                    }

                    <SidebarGroupContent>
                        <SidebarMenu>
                            {group.items.map((item, itemIndex) => (
                                <SidebarMenuItem key={`G-${index}-I-${itemIndex}`}>
                                    <SidebarMenuLink menu={item} />
                                </SidebarMenuItem>
                            ))}
                        </SidebarMenu>
                    </SidebarGroupContent>
                </SidebarGroup>
            ))}
            </SidebarContent>

            <SidebarBaseFooter loginUser={loginUser} />
        </Sidebar>
    )
}
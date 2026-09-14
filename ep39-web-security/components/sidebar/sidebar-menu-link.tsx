'use client'

import { setActiveFeature } from "@/lib/services/actions/active-feature.action";
import { SidebarMenuButton } from "../ui/sidebar";
import { SidebarMenuItem } from "./sidebar-base";
import { useRouter } from "next/navigation";

export default function SidebarMenuLink({menu} : {menu : SidebarMenuItem}) {

    const router = useRouter()

    async function click() {
        if(menu.feature) {
            await setActiveFeature(menu.feature)
        }
        router.push(menu.link)
    }

    return (
        <SidebarMenuButton onClick={click}>
            {menu.icon} {menu.name}
        </SidebarMenuButton>
    )
}
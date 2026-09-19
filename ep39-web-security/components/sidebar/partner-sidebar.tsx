import { Briefcase, Home, Megaphone, Speaker, User, UserCog } from "lucide-react";
import SidebarBase, { SidebarMenuGroup } from "./sidebar-base";

const MENUS:SidebarMenuGroup[] = [
    {
        items: [
            {name : "Partner Home", link : "/partner", icon : <Home />}
        ]
    },
    {
        name: "Profile",
        items: [
            {name : "User Profile", link : "/partner", icon : <User />},
            {name : "Company Profile", link : "/partner", icon : <Briefcase />},
        ]
    },
    {
        name: "Management",
        items: [
            {name : "Member Management", link : "/partner", icon : <UserCog />},
            {name : "Job Post Management", link : "/partner", icon : <Megaphone />},
        ]
    },

]

export default function PartnerSidebar() {
    return (
        <SidebarBase menus={MENUS} title="Partner" />
    )
}
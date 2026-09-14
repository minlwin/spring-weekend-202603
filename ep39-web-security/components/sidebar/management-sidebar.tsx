import { Briefcase, Home, Megaphone, ShieldUser, Users } from "lucide-react";
import SidebarBase, { SidebarMenuGroup } from "./sidebar-base";

const MENUS:SidebarMenuGroup[] = [
    {
        items: [
            {name: "Management Home", icon: <Home />, link: "/management", feature: "Dashboard"},
            {name: "Job Post Management", icon: <Megaphone />, link: "/management/jobs", feature: "Job Post Management"},
        ]
    },
    {
        name: "Account Management",
        items: [
            {name: "Partners", icon: <Briefcase />, link: "/management/partners", feature : "Partner Management"},
            {name: "Candidates", icon: <Users />, link: "/management/candidates", feature : "Candidate Management"},
            {name: "Employees", icon: <ShieldUser />, link: "/management/employees", feature : "Employee Management"},
        ]
    }
]

export default function ManagementSideBar() {
    return (
        <SidebarBase title="Management" menus={MENUS} />
    )
}
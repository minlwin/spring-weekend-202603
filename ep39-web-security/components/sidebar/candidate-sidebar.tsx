import { Briefcase, GraduationCap, HeartPlus, Home, Shield, ShieldCheck, SquarePen, StarCheck, User } from "lucide-react";
import SidebarBase, { SidebarMenuGroup } from "./sidebar-base";

const MENUS:SidebarMenuGroup[] = [
    {items : [
        {name : "Home", link: "/candidate", "icon" : <Home />, feature : "Dashboard"},
    ]},
    {
        name: "Profile",
        items: [
            {name : "Personal Information", link: "/candidate/personal-info", "icon" : <User />, feature : "Personal Information"},
            {name : "Education History", link: "/candidate/educations", "icon" : <GraduationCap />, feature : "Education History"},
            {name : "Certificates", link: "/candidate/certificates", "icon" : <ShieldCheck />, feature : "Certificates"},
            {name : "Job History", link: "/candidate/jobs", "icon" : <Briefcase />, feature : "Job History"},
        ]
    },
    {
        name: "Job Posts",
        items: [
            {name : "Applications", link: "/candidate/applications", "icon" : <SquarePen />, feature : "Job Applications"},
            {name : "Interest Company", link: "/candidate/companies", "icon" : <StarCheck />, feature : "Interest Company"},
            {name : "Wish List", link: "/candidate/wishes", "icon" : <HeartPlus />, feature : "Wish List"},
        ]
    }
]

export default function CandidateSidebar() {
    return (
        <SidebarBase menus={MENUS} title="Partner" />
    )
}
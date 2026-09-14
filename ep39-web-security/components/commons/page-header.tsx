import { getFeature } from "@/lib/services/storage/active-feature";
import { SidebarTrigger } from "../ui/sidebar";
import { ChevronRight } from "lucide-react";

export default async function PageHeader({title} : {title : String}) {
    const feature = await getFeature()
    return (
        <header className="py-3 flex items-center border-b gap-4">
            <span className="flex items-center">
                <SidebarTrigger /> {title}
            </span>
            <ChevronRight size={16} />
            <span>{feature || 'Dashboard'}</span> 
        </header>
    )
}
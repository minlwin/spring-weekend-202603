import { SidebarTrigger } from "../ui/sidebar";

export default function PageHeader({title} : {title : String}) {
    return (
        <header className="py-3 flex items-center border-b">
            <SidebarTrigger /> {title}
        </header>
    )
}
import LogoutButton from "@/components/commons/logout-button";
import { LayoutsProps } from "@/lib/types";

export default function ManagementLayout({children} : LayoutsProps) {
    return (
        <div>
            <nav>

                <LogoutButton />
            </nav>
            {children}
        </div>
    )
}
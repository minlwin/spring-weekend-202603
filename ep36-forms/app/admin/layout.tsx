import LogoutMenu from "@/components/custom/logout-menu";
import { NavigationMenu, NavigationMenuItem, NavigationMenuLink, NavigationMenuList } from "@/components/ui/navigation-menu";
import { Users } from "lucide-react";
import React from "react";

export default function AdminLayout({children} : {children : React.ReactNode}) {
    return (
        <div>

            <header className="flex justify-between py-4 px-16">
                <h1 className="font-semibold text-2xl">Admin Portal</h1>

                <NavigationMenu>
                    <NavigationMenuList>
                        <NavigationMenuItem>
                            <NavigationMenuLink>
                                <Users /> Students
                            </NavigationMenuLink>
                        </NavigationMenuItem>
                        <NavigationMenuItem>
                            <LogoutMenu />
                        </NavigationMenuItem>
                    </NavigationMenuList>
                </NavigationMenu>
            </header>

            <main className="px-16 py-2">
                {children}
            </main>
        </div>
    )
}
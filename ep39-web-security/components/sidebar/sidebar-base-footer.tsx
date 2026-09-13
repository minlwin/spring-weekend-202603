'use client'

import { SidebarFooter, SidebarMenu, SidebarMenuButton, SidebarMenuItem } from "../ui/sidebar"
import * as action from "@/lib/services/actions/security.action"
import { DropdownMenu, DropdownMenuContent, DropdownMenuGroup, DropdownMenuItem, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger } from "../ui/dropdown-menu"
import { Avatar, AvatarFallback } from "../ui/avatar"
import { ChevronRight, Key, LogOut, User } from "lucide-react"
import { LoginUser } from "@/lib/types"

export default function SidebarBaseFooter({loginUser} : {loginUser? : LoginUser}) {

    async function logout() {
        await action.signOut()
    }

    return (
        <SidebarFooter>
            <SidebarMenu>
                <SidebarMenuItem>
                    <DropdownMenu>
                        <DropdownMenuTrigger render={(props) => 
                            <SidebarMenuButton {...props} >
                                <User /> {loginUser?.name}
                                <ChevronRight className="ml-auto" />
                            </SidebarMenuButton>
                        } />    

                        <DropdownMenuContent side="right" align="end" sideOffset={4}>
                            <DropdownMenuGroup>
                                <DropdownMenuLabel className={'flex items-center gap-4'}>
                                    <Avatar>
                                        <AvatarFallback>
                                            <User />
                                        </AvatarFallback>
                                    </Avatar>

                                    <div className="flex flex-col">
                                        <span>{loginUser?.name}</span>
                                        <span>{loginUser?.email}</span>
                                    </div>
                                </DropdownMenuLabel>

                            </DropdownMenuGroup>

                            <DropdownMenuSeparator />

                            <DropdownMenuItem>
                                <User /> Profile
                            </DropdownMenuItem>

                            <DropdownMenuItem>
                                <Key /> Change Password
                            </DropdownMenuItem>

                            <DropdownMenuSeparator />

                            <DropdownMenuItem onClick={logout}>
                                <LogOut /> Logout
                            </DropdownMenuItem>
                        </DropdownMenuContent>
                    </DropdownMenu>
                </SidebarMenuItem>
            </SidebarMenu>
        </SidebarFooter>
    )
}
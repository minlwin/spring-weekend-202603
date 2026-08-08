'use client'

import { LogOut } from "lucide-react"
import { NavigationMenuLink } from "../ui/navigation-menu"
import { useRouter } from "next/navigation"
import { useLoginUser } from "@/lib/context/login-user-context"

export default function LogoutMenu() {

    const router = useRouter()
    const { setUser } = useLoginUser()

    function logout() {
        setUser(undefined)
        router.replace('/signin')
    }

    return (
        <NavigationMenuLink onClick={logout}>
            <LogOut /> Logout
        </NavigationMenuLink>
    )
}
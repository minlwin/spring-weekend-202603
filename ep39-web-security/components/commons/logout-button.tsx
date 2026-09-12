'use client'

import { LogOut } from "lucide-react"
import { Button } from "../ui/button"
import * as action from "@/lib/services/actions/logout.action"
import { useLoginUserContxt } from "@/lib/state/login-user.context"
import { useRouter } from "next/navigation"

export default function LogoutButton() {

    const {setLoginUser} = useLoginUserContxt()
    const router = useRouter()

    async function logout() {
        await action.logout()
        setLoginUser(undefined)
        router.replace("/")
    }

    return (
        <Button onClick={logout}>
            <LogOut /> Logout
        </Button>
    )
}
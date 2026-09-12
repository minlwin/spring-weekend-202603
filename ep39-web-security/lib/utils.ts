import { redirect } from "next/navigation";
import { ClientError, Role } from "./types";
import { toast, ToastT } from "sonner";

export { cn } from "cn"

export function getHome(role: Role) : string {
    if(role == "Admin" || role == "Employee") {
        return "/management"
    }

    return `/${role.toLocaleLowerCase()}`
}

export async function safeCall(action : () => Promise<void>) {
    try {
        await action()
    } catch (e : any) {
        const error:ClientError = JSON.parse(e.message)

        if(error.status == 401 || error.status == 403) {
            const message = error.messages.length > 0 ? error.messages[0] : "You have to login for this operation."
            redirect(`/signin?message=${message}`)
        } else {
            const message:Partial<ToastT> = {
                description: error.messages,
                type: error.status === 500 ? "error" : "warning"
            }

            toast("Message", message)
        }
    }
}
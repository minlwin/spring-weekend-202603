import { redirect } from "next/navigation";
import { ClientError, Role } from "./types";
import { toast, ToastT } from "sonner";
import { isRedirectError } from "next/dist/client/components/redirect-error";
import { format, formatISO } from "date-fns";

export { cn } from "cn"

export function getHome(role: Role) : string {
    if(role == "Admin" || role == "Employee") {
        return "/management"
    }

    if(role == "Partner" || role == "Member") {
        return "/partner"
    }


    return `/candidate`
}

export async function safeCall(action : () => Promise<void>) {
    try {
        await action()
    } catch (e : any) {
        if(!isRedirectError(e) && e.message) {

            const error:ClientError = JSON.parse(e.message)

            const message:Partial<ToastT> = {
                description: error.messages,
                type: error.status === 500 ? "error" : "warning"
            }

            toast("Message", message)
        } else {
            console.log(e)
        }
    }
}

export function formatDateTime(value? : string) {
    if(value) {
        const dateTime = formatISO(value)
        return format(dateTime, 'yyyy-MM-dd HH:mm')
    }
    return ""
}

export function resources(path: string) {
    const baseUrl = process.env.BASE_API
    return `${baseUrl}/resources/${path}`
}
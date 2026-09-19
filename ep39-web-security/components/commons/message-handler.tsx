'use client'

import { useSearchParams } from "next/navigation";
import React, { useEffect } from "react";
import { toast, ToastT } from "sonner";

export default function MessageHandler({children} : {children : React.ReactNode}) {

    const searchParams = useSearchParams()
    const message = searchParams.get("message")

    useEffect(() => {
        if(message) {
            const toastMessage:Partial<ToastT> = {
                description: message,
                type: "warning"
            }

            toast("Message", toastMessage)
        }
    }, [message])

    return (
        <>
            {children}
        </>
    )
}
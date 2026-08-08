import { Button } from "@/components/ui/button";
import { Coffee, Home } from "lucide-react";
import Link from "next/link";
import React from "react";

export default function AuthLayout({children} : {children : React.ReactNode}) {
    return (
        <div className="min-h-screen flex">

            <header className="flex-1 bg-gray-200 flex items-center justify-center">

                <div className="flex flex-col items-center gap-2">
                    <Coffee size={180} />
                    <h1 className="text-3xl">Java Developer Class</h1>
                    <Button nativeButton={false} render={<Link href="/" />}>
                        <Home /> Home
                    </Button>
                </div>
            </header>

            <main className="flex-1 flex items-center justify-center">
                <div className="w-2/3">
                    {children}
                </div>
            </main>
        </div>
    )
}
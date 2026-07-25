import { Button } from "@/components/ui/button";
import { Home, RouteIcon } from "lucide-react";
import Link from "next/link";
import React from "react";

export default function AuthLayout({children} : {children : React.ReactNode}) {
    return (
        <div className="flex h-screen">
            <div className="bg-cyan-500 flex-1 flex items-center justify-center">
                <section className="flex items-center justify-center flex-col gap-6">
                    <RouteIcon size={160} className="text-cyan-800" />

                    <h1 className="text-4xl">Routing and Navigations</h1>

                    <Button variant={'outline'} nativeButton={false} render={<Link href="/" />}>
                        <Home /> Home
                    </Button>
                </section>
            </div>

            <div className="flex-1 flex items-center justify-center">
                <section className="w-2/4">
                    { children }
                </section>
            </div>
        </div>
    )
}
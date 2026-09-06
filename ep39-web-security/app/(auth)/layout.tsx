import { Button } from "@/components/ui/button";
import { LayoutsProps } from "@/lib/types";
import { BriefcaseBusiness, Home } from "lucide-react";
import Link from "next/link";

export default function AuthLayout({children} : LayoutsProps) {
    return (
        <div className="flex h-screen">
            <div className="flex-1 bg-gray-200 flex flex-col items-center justify-center">
                <BriefcaseBusiness size={120} />
                <h1 className="text-3xl">Hello JOB</h1>
                <h3 className="mb-4">Discover jobs. Build your future.</h3>
                <Button render={<Link href={'/'} />} nativeButton={false}>
                    <Home /> Home
                </Button>
            </div>

            <main className="flex-1 flex items-center justify-center">
                <section className="w-2/3">
                    {children}
                </section>
            </main>
        </div>
    )
}
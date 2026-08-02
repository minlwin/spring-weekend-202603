import React from "react";
import Navbar from "./_commons/navbar";

export default function BusinessLayout({children} : {children : React.ReactNode}) {
    return (
        <div>
            <Navbar />
            <main className="px-16 py-4">
                {children}
            </main>
        </div>
    )
}
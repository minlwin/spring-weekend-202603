import type React from "react";

export default function Layout({children} : {
    children : React.ReactNode
}) {
    return (
        <main>
            <div>
            </div>
            <div>
                {children}
            </div>
        </main>
    )
}
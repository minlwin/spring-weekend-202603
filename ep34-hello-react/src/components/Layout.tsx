import type React from "react";

export default function Layout({title, action, children} : {
    title: string,
    action? : React.ReactNode,
    children : React.ReactNode
}) {
    return (
        <>
            <header className="pageHeader">
                <h3>{title}</h3>

                {action}
            </header>

            <main>
                {children}
            </main>
        </>
    )
}
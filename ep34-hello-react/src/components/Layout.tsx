import type React from "react";
import Menu from "./Menu";

export default function Layout({title, action, children} : {
    title: string,
    action? : React.ReactNode,
    children : React.ReactNode
}) {
    return (
        <main>
            <Menu />
            <div className="container">
                <header className="pageHeader">
                    <h3>{title}</h3>

                    {action}
                </header>

                <main>
                    {children}
                </main>
            </div>
        </main>
    )
}
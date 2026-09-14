import React from "react";
import PageTitle from "./page-title";

export default function PageTemplate({title, children} : {title : string, children : React.ReactNode}) {
    return (
        <section className="space-y-6">
            <PageTitle title={title} />
            {children}
        </section>
    )
}
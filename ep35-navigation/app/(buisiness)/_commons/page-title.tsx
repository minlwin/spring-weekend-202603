import React from "react";

export default function PageTitle({name, action} : {name : string, action?: React.ReactNode}) {
    return (
        <div className="flex items-center justify-between">
            <h1 className="text-3xl uppercase font-bold">{name}</h1>

            {action}
        </div>
    )
}
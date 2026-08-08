'use client'

import React, { useState } from "react";
import { LoginUserContext } from "./login-user-context";
import { LoginResult } from "../schema/auth-schema";

export default function LoginUserProvider({children} : {children : React.ReactNode}) {
    const [user, setUser] = useState<LoginResult>()

    return (
        <LoginUserContext.Provider value={{user: user, setUser: setUser}}>
            {children}
        </LoginUserContext.Provider>
    )
}
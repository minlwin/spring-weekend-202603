'use client'

import { useState } from "react";
import { LayoutsProps, LoginUser } from "../types";
import { LoginUserContext } from "./login-user.context";

export default function LoginUserProvider({children} : LayoutsProps) {

    const [loginUser, setLoginUser] = useState<LoginUser>()

    return (
        <LoginUserContext.Provider value={{loginUser: loginUser, setLoginUser: setLoginUser}}>
            {children}
        </LoginUserContext.Provider>
    )
}
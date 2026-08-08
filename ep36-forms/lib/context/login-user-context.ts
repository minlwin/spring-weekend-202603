import { createContext, useContext } from "react"
import { LoginResult } from "../schema/auth-schema"

export type LoginUserContextType = {
    user?: LoginResult
    setUser: (user?: LoginResult) => void
}

export const LoginUserContext = createContext<LoginUserContextType | undefined>(undefined)

export function useLoginUser() {
    const context = useContext(LoginUserContext)

    if(!context) {
        throw new Error("Invalid usage of login user context.")
    }

    return context
}
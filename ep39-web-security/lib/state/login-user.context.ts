import { createContext, useContext } from "react"
import { LoginUser } from "../types"

type LoginUserContextType = {
    loginUser?: LoginUser
    setLoginUser: (loginUser?: LoginUser) => void
}

export const LoginUserContext = createContext<LoginUserContextType | undefined>(undefined)

export function useLoginUserContxt() {
    const context = useContext(LoginUserContext)

    if(!context) {
        throw new Error("Invalid usage of Login User Context")
    }

    return context
}
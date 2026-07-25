import { createContext, useContext } from "react"

export type MenuState = {
    selected: string
    setSelected: (menu: string) => void
}

export const MenuStateContext = createContext<MenuState | undefined>(undefined)

export function useMenuState() {
    const context = useContext(MenuStateContext)

    if(!context) {
        throw new Error("Invalid usage of Menu Context.")
    }

    return context
}

import { createContext, useContext } from "react";
import type { MenuAction } from ".";

export const SelectedMenuContext = createContext<MenuAction | undefined>(undefined)

export function useMenuAction() {
    const context = useContext(SelectedMenuContext)

    if(!context) {
        throw new Error("Invalid usage of Menu Action Context")
    }

    return context
}
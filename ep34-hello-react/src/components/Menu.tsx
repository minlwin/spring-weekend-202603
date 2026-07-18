import type { MenuType } from "../model"
import { useMenuAction } from "../model/menu.context"

export function Menu() {
    return (
        <nav className="navbar">
            <span className="appName">React Component</span>

            <div>
                <MenuItem name="Welcome" />
                <MenuItem name="State"  />
                <MenuItem name="Interaction"  />
                <MenuItem name="Effect"  />
                <MenuItem name="Reference"  />
                <MenuItem name="Reducer"  />
                <MenuItem name="Context"  />
            </div>
        </nav>
    )
}

function MenuItem({name } : {name : MenuType}) {

    const menuAction = useMenuAction()

    return (
        <span className='menuItem' onClick={() => menuAction(name)} >{name}</span>
    )
}
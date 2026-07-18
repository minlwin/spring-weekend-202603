import { useState } from "react"
import WelcomePage from "./pages/WelcomePage"
import ComponentStatePage from "./pages/ComponentStatePage"
import CalculatorPage from "./pages/CalculatorPage"
import EffectDemoPage from "./pages/EffectDemoPage"

export default function Application() {

    const [selectedMenu, setSelectedMenu] = useState<MenuType>('Welcome')

    return (
        <main>
            <Menu menuAction={setSelectedMenu}/>

            <div className="container">
                {selectedMenu == 'Welcome' && 
                    <WelcomePage />
                }

                {selectedMenu == 'State' && 
                    <ComponentStatePage />
                }

                {selectedMenu == 'Interaction' && 
                    <CalculatorPage />
                }

                {selectedMenu == 'Effect' && 
                    <EffectDemoPage />
                }

            </div>
        </main>
    )
}

function Menu({menuAction} : {menuAction : MenuAction}) {
    return (
        <nav className="navbar">
            <span className="appName">React Component</span>

            <div>
                <MenuItem name="Welcome" onMenuClick={menuAction}/>
                <MenuItem name="State"  onMenuClick={menuAction}/>
                <MenuItem name="Interaction"  onMenuClick={menuAction}/>
                <MenuItem name="Effect"  onMenuClick={menuAction}/>
                <MenuItem name="Reference"  onMenuClick={menuAction}/>
                <MenuItem name="Reducer"  onMenuClick={menuAction}/>
                <MenuItem name="Context"  onMenuClick={menuAction}/>
            </div>
        </nav>
    )
}

type MenuType = 
    'Welcome' | 'State' | 'Interaction' | 'Effect' | 'Reference' | 'Reducer' | 'Context'

interface MenuAction {
    (menu:MenuType) : void
}

function MenuItem({name, onMenuClick} : {name : MenuType, onMenuClick : MenuAction}) {

    return (
        <span className='menuItem' onClick={() => onMenuClick(name)}>{name}</span>
    )
}

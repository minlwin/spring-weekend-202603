import { useState } from "react"
import WelcomePage from "./pages/WelcomePage"
import ComponentStatePage from "./pages/ComponentStatePage"
import CalculatorPage from "./pages/CalculatorPage"
import EffectDemoPage from "./pages/EffectDemoPage"
import type { MenuType } from "./model"
import { Menu } from "./components/Menu"
import { SelectedMenuContext } from "./model/menu.context"
import ReducerDemoPage from "./pages/ReducerDemoPage"

export default function Application() {

    const [selectedMenu, setSelectedMenu] = useState<MenuType>('Welcome')

    return (
        <SelectedMenuContext.Provider value={setSelectedMenu}>
            <main>
                <Menu />

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

                    {selectedMenu == 'Reducer' && 
                        <ReducerDemoPage />
                    }

                </div>
            </main>
        </SelectedMenuContext.Provider>
    )
}


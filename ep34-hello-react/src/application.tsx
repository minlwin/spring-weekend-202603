import { useState } from "react";
import { Menu } from "./components/Menu";
import WelcomePage from "./pages/WelcomePage";
import ComponentStatePage from "./pages/ComponentStatePage";
import CalculatorPage from "./pages/CalculatorPage";
import ReducerDemoPage from "./pages/ReducerDemoPage";
import { MenuStateContext } from "./context/menu-state.context";

export default function Application() {

    const [selected, setSelected] = useState("Welcome")

    return (
        <MenuStateContext.Provider value={{selected: selected, setSelected: setSelected}}>
            <Menu />

            <main className="container mt-3">
                {selected === 'Welcome' && <WelcomePage />}
                {selected === 'State' && <ComponentStatePage />}
                {selected === 'Interaction' && <CalculatorPage />}
                {selected === 'Reducer' && <ReducerDemoPage />}
            </main>
        </MenuStateContext.Provider>
    )
}


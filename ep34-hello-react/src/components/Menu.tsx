import { useMenuState } from "../context/menu-state.context"

export function Menu() {
    return (
        <nav className="navbar navbar-expand navbar-dark bg-primary">
            <div className="container">
                <span className="navbar-brand">React Component</span>

                <div className="navbar-nav">
                    <MenuItem name="Welcome" />
                    <MenuItem name="Interaction"  />
                    <MenuItem name="State"  />
                    <MenuItem name="Reducer" />
                </div>
            </div>
        </nav>
    )
}

function MenuItem({name } : {name : string}) {

    const {selected, setSelected} = useMenuState()

    return (
        <div className="nav-item">
            <a className={`nav-link ${selected == name ? 'active' : ''}`} onClick={() => setSelected(name)} >{name}</a>
        </div>
    )
}
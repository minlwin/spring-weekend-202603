export default function Menu() {
    return (
        <nav className="navbar">
            <span className="appName">React Component</span>

            <div>
                <MenuItem name="Welcome" />
                <MenuItem name="Component State" />
                <MenuItem name="User Interaction" />
            </div>
        </nav>
    )
}


function MenuItem({name} : {name : string}) {

    const clickMenu = () => {
        console.log(`Click ${name}`)
    }

    return (
        <span className='menuItem' onClick={clickMenu}>{name}</span>
    )
}

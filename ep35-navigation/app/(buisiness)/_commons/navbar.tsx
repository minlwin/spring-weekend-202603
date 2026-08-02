import Link from "next/link";

export default function Navbar() {
    return (
        <nav className="bg-gray-600 text-white">
            <div className="flex justify-between items-center mx-16">
                <h3 className="text-2xl font-semibold">
                    <Link href={'/'}>Home</Link>
                </h3>

                <div>
                    <MenuLink name="Classes" url="/classes" />
                    <MenuLink name="Courses" url="/courses" />
                    <MenuLink name="Sign In" url="/signin" />
                </div>
            </div>
        </nav>
    )
}

function MenuLink({name, url} : {name: string, url: string}) {
    return (
        <Link className="inline-block px-4 py-4 hover:bg-gray-400" href={url}>{name}</Link>
    )
}
export default function PageTitle({title} : {title : string}) {
    return (
        <header>
            <h3 className="text-2xl font-semibold">{title}</h3>
        </header>
    )
}
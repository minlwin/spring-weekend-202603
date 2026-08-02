export default function PageTitle({name} : {name : string}) {
    return (
        <div>
            <h1 className="text-3xl uppercase font-bold">{name}</h1>
        </div>
    )
}
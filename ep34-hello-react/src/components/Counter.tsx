import { useState } from "react"

export default function Counter({name} : {name : string}) {

    const [count, setCount] = useState(0)
    const countUp = () => {
        setCount(count + 1)
    }

    return (
        <section className="card">
            <div className="card-body text-center">
                <h3>{name}</h3>

                <div>
                    <h1>{count}</h1>
                </div>

                <div onClick={countUp} className="btn btn-primary w-100">
                    Count Up
                </div>
            </div>
        </section>
    )
}
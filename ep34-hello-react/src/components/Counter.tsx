import { useState } from "react"

export default function Counter({name} : {name : string}) {

    const [count, setCount] = useState(0)
    const countUp = () => {
        setCount(count + 1)
    }

    return (
        <section className="counter">
            <h3>{name}</h3>

            <div>
                <h1>{count}</h1>
            </div>

            <div onClick={countUp} className="btn">
                Count Up
            </div>
        </section>
    )
}
import { useState } from "react";
import Counter from "../components/Counter";
import Layout from "../components/Layout";

export default function ComponentStatePage() {

    const [count, setCount] = useState(1)

    const array:string[] = []

    for(let i = 0; i < count; i ++) {
        array.push(`Counter ${i + 1}`)
    }

    return (
        <Layout title="About Component State" action={
            <div style={{display : 'flex', gap: '0.5rem'}}>
                <ActionButton name="Add Counter" action={() => setCount(count + 1)} />
                <ActionButton name="Remove One" action={() => setCount(count - 1)} />
            </div>
        }>
            <section className="grid grid-cols-3">
                {array.map((value, index) => 
                    <Counter key={index} name={value} />
                )}
            </section>
        </Layout>
    )
}


function ActionButton({name, action} : {name : string, action : () => void }) {
    return (
        <div className="btn" onClick={action}>{name}</div>
    )
}

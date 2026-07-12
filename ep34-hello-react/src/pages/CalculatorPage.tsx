import { useState } from "react";
import Layout from "../components/Layout";

type Operator = "+" | "-" | "*" | "/" | "%"

function calculate(d1: number, d2: number, ope :Operator) {
    switch(ope) {
        case "+":return d1 + d2
        case "-":return d1 - d2
        case "*":return d1 * d2
        case "/":return d1 / d2
        case "%":return d1 % d2
    }
}

export default function CalculatorPage() {

    const [temp, setTemp] = useState<string>()
    const [lastOpe, setLastOpe] = useState<Operator>()

    const [result, setResult] = useState("0")

    const [clean, setClean] = useState(true)

    function pressNum(value:number) {
        if(result === "0" || clean) {
            setResult(`${value}`)
        } else {
            setResult(`${result}${value}`)
        }
        setClean(false)
    }

    function pressOperator(ope:Operator) {
        if(result !== "0") {
            if(!lastOpe) {
                setTemp(result)
            } else {
                const d1 = Number.parseFloat(temp ?? '0')
                const d2 = Number.parseFloat(result)
                const calcResult = calculate(d1, d2, lastOpe)
                setTemp(calcResult.toString())
            }
            setLastOpe(ope)
            setResult("0")
        }
    }

    function deleteOne() {
        if(result !== "0") {
            if(result.length > 1) {
                 setResult(result.substring(0, result.length - 1))
            } else {
                setResult("0")
            }
        }
    }

    function clear() {
        setTemp(undefined)
        setLastOpe(undefined)
        setResult("0")
        setClean(true)
    }

    function doDecimal() {
        if(!result.includes(".")) {
            setResult(`${result}.`)
        } 
        setClean(false)
    }

    function switchNegative() {
        if(result !== "0") {
            if(result.startsWith("-")) {
                setResult(result.substring(1))
            } else {
                setResult(`-${result}`)
            }
        }
    }

    function doCalculate() {
        if(result !== "0") {
            if(lastOpe) {
                const d1 = Number.parseFloat(temp ?? '0')
                const d2 = Number.parseFloat(result)
                const calcResult = calculate(d1, d2, lastOpe)
                setResult(calcResult.toString())
            }

            setTemp(undefined)
            setLastOpe(undefined)
            setClean(true)
        }
    }

    return (
        <Layout title="Calulator">
            <section id="calculator">
                <div className="temp">
                    {temp}
                </div>
                <div className="result">
                    {result}
                </div>
                <div className="buttons">
                    <ActionButton value="D" varient="secondary" action={deleteOne} />
                    <ActionButton value="C" varient="secondary" action={clear} />
                    <ActionButton value="%" varient="secondary" action={() => pressOperator("%")} />
                    <ActionButton value="/" action={() => pressOperator("/")}/>
                    <NumberButton value={7} pressNumber={pressNum} />
                    <NumberButton value={8} pressNumber={pressNum} />
                    <NumberButton value={9} pressNumber={pressNum} />
                    <ActionButton value="*" action={() => pressOperator("*")}/>
                    <NumberButton value={4} pressNumber={pressNum} />
                    <NumberButton value={5} pressNumber={pressNum} />
                    <NumberButton value={6} pressNumber={pressNum} />
                    <ActionButton value="-" action={() => pressOperator("-")}/>
                    <NumberButton value={1} pressNumber={pressNum} />
                    <NumberButton value={2} pressNumber={pressNum} />
                    <NumberButton value={3} pressNumber={pressNum} />
                    <ActionButton value="+" action={() => pressOperator("+")}/>
                    <ActionButton value="+/-" varient="gray" action={switchNegative} />
                    <NumberButton value={0} pressNumber={pressNum} />
                    <ActionButton value="." varient="gray" action={doDecimal} />
                    <ActionButton value="=" action={doCalculate} />
                </div>
            </section>
        </Layout>
    )
}

function NumberButton({value, pressNumber} : {
    value : number, 
    pressNumber : (value:number) => void
}) {
    return (
        <div className="calcBtn bgGray" onClick={() => pressNumber(value)}>{value}</div>
    )
}

function ActionButton({value, varient = 'primary', action} : {
    value : string, 
    varient? : 'primary' | 'secondary' | 'gray',
    action? : VoidFunction
}) {
    return (
        <div className={`calcBtn ${varient}`} onClick={action}>{value}</div>
    )
}
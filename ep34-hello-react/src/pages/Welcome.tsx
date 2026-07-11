interface WelcomeProps {
    value : string,
    color? : string,
    fontSize? : "1rem" | "1.5rem" | "2rem" | "3rem"
}

export default function Welcome({
    value, color, fontSize = "1rem"
} : WelcomeProps) {
    return <h1 style={{color: color, fontSize : fontSize}}>{value}</h1>
}
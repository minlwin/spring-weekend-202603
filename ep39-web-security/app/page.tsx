import Link from "next/link";

export default function WelcomePage() {
  return (
    <main>
      <h1>Hello JOB</h1>

      <div>
        <Link href={'/signin'}>
          Sign In
        </Link>
      </div>
    </main>
  )
}
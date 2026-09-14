import { Suspense } from "react"
import SignInComponent from "@/components/commons/signin-component"
import Loading from "@/components/commons/loading"

export default function SignInPage() {
    return (
        <Suspense fallback={<Loading />}>
            <SignInComponent />
        </Suspense>
    )
}
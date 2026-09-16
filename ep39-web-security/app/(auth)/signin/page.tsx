import { Suspense } from "react"
import SignInComponent from "@/app/(auth)/signin/_client/signin-component"
import Loading from "@/components/commons/loading"

export default function SignInPage() {
    return (
        <Suspense fallback={<Loading />}>
            <SignInComponent />
        </Suspense>
    )
}
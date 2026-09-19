import CandidateSignUpComponent from "../_client/candidate-signup-component"
import PartnerSignUpComponent from "../_client/partner-signup-component"

export default async function SignUpPage({ params } : PageProps<'/signup/[type]'>) {

    const { type } = await params

    if(type === 'partner') {
        return (
            <PartnerSignUpComponent />
        )
    }

    return (
        <CandidateSignUpComponent />
    )
}
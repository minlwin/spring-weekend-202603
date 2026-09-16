import ActivationFormComponent from "./_client/activation-form-component";

export default async function ActivationPage({ searchParams } : PageProps<'/activation'>) {
    const resolvedSearchParams = await searchParams
    const message = resolvedSearchParams.message as string | undefined
    return (
        <ActivationFormComponent message={message} />
    )
}
import ResetPasswordFormComponent from "../_client/reset-password-component";

export default async function ResetPasswordPage({ params } : PageProps<'/password/[id]'>) {
    const { id } = await params
    return (
        <ResetPasswordFormComponent id={id} />
    )
}
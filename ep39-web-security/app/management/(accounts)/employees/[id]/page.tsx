import * as action from '@/lib/services/actions/management/employee-management.action'

export default async function EmployeeDetailsPage({ params } : PageProps<'/management/employees/[id]'>) {

    const { id } = await params 
    const employee = await action.findById(id)

    return (
        <></>
    )
}
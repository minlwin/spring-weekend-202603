import * as action from '@/lib/services/actions/management/employee-management.action'
import EmployeeEditForm from '../_client/employee-edit-form'

export default async function EmployeeUpdatePage({ params } : PageProps<'/management/employees/edit/[id]'>) {
    const { id } = await params 
    const employee = await action.findById(id)

    return (
        <EmployeeEditForm id={id} value={employee} />
    )
}
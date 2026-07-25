import type { User } from "../context/user-state.context";

export default function UserListView({list, onEdit, onDelete} : {list : User[], onEdit?: (data: User) => void, onDelete? : (id: number) => void}) {
    return (
        <table className="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>

            <tbody>
                {list.map(user => 
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.name}</td>
                        <td>{user.phone}</td>
                        <td>{user.email}</td>
                        <th>
                            <a className="btn-link" onClick={() => onEdit?.(user)} >
                                Edit
                            </a>
                        </th>
                        <th>
                            <a className="btn-link" onClick={() => onDelete?.(user.id)} >
                                Delete
                            </a>
                        </th>
                    </tr>
                )}
            </tbody>
        </table>
    )
}
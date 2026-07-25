import { useEffect, useReducer, useRef, useState } from "react";
import Layout from "../components/Layout";
import UserEditForm from "../components/UserEditForm";
import { userReducer, type User, type UserForm } from "../context/user-state.context";
import UserListView from "../components/UserListView";

const DEFAULT_USER:UserForm = {
    name: "",
    phone: "",
    email: ""
}

export default function ReducerDemoPage() {

    const [users, usersDispatch] = useReducer(userReducer, [])
    const [form, setForm] = useState<UserForm>({...DEFAULT_USER})
    const [editId, setEditId] = useState<number>()

    const id = useRef(0)

    useEffect(() => {

        const currentId = localStorage.getItem("currentId")
        id.current = Number.parseInt(currentId || "0")

        const userValues = localStorage.getItem("users") || '[]'
        usersDispatch({type : 'Reset', data : JSON.parse(userValues)})

        return () => {
            localStorage.setItem("currentId", id.current.toString())
        }
    }, [])

    useEffect(() => {
        localStorage.setItem('users', JSON.stringify(users))
    }, [users])

    function saveUser(form : UserForm) {
        if(editId) {
            usersDispatch({type: 'Update', id: editId, form})
        } else {
            id.current = id.current + 1
            usersDispatch({type: 'Create', id: id.current, form})
        }
        setEditId(undefined)
        setForm({...DEFAULT_USER})
    }

    function edit(user: User) {
        const {id, ...editForm} = user
        setForm({...editForm})
        setEditId(id)
    }

    return (
        <Layout title="Use Reducer Hook">
            <div className="row mt-3">
                <div className="col-4">
                    {/* User Edit Form */}
                    {editId ? 
                        <div>
                            <UserEditForm hasId={editId !== undefined} key={users.length + 1} data={form} onSave={saveUser} />
                        </div>    
                        : 
                        <UserEditForm hasId={editId !== undefined} key={users.length + 1} data={form} onSave={saveUser} />
                    }
                </div>
                
                <div className="col">
                    {/* User List View */}
                    <UserListView list={users} onEdit={edit} onDelete={id => {
                        usersDispatch({type : 'Delete', id: id})
                    }} />
                </div>
            </div>
        </Layout>
    )
}

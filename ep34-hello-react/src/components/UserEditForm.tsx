import { useEffect, useRef, useState } from "react"
import type { UserForm } from "../context/user-state.context"

export default function UserEditForm({hasId, data, onSave} : {hasId:boolean, data: UserForm, onSave: (data: UserForm) => void}) {

    const [form, setForm] = useState<UserForm>(data)

    const isValid = form.name && form.email && form.phone
    const nameInput = useRef<HTMLInputElement | null>(null)

    useEffect(() => nameInput.current?.focus(), [nameInput])

    return (
        <div className="card">
            <div className="card-header">
                <h5 className="card-title">{hasId ? 'Edit' : 'Create'} User</h5>
            </div>
            <div className="card-body">
                <div className="mb-3">
                    <label className="form-label">Name</label>
                    <input ref={nameInput} type="text" className="form-control" placeholder="Enter Name" 
                        value={form.name} onChange={e => setForm({...form, name : e.target.value})} />
                </div>
                <div className="mb-3">
                    <label className="form-label">Phone</label>
                    <input type="tel" className="form-control" placeholder="Enter Phone" 
                        value={form.phone} onChange={e => setForm({...form, phone: e.target.value})} />
                </div>
                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input type="email" className="form-control"placeholder="Enter Email" 
                        value={form.email} onChange={e => setForm({...form, email: e.target.value})} />
                </div>

                <button onClick={() => onSave(form)} className="btn btn-primary" disabled={!isValid}>Save User</button>
            </div>
        </div>
    )
}
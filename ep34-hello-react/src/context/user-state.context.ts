export interface User {
    id: number
    name: string
    phone: string
    email: string
} 

export type UserForm = Omit<User, 'id'>

export type UserAction = 
    { type : 'Create', id: number, form: UserForm } |
    { type : 'Update', id: number, form: UserForm } |
    { type : 'Delete', id: number } | 
    { type : 'Reset', data : User[]}


export function userReducer(state : User[], action : UserAction) {
    switch (action.type) {
        case 'Create':
            return [...state, {id: action.id, ...action.form}]
        case 'Update':
            return state.map(a => {
                if(a.id == action.id) {
                    return {id: action.id, ...action.form}
                } 
                return a
            })    
        case 'Delete':
            return state.filter(a => a.id != action.id)
        case 'Reset' :
            return action.data
    }
}
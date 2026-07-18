import type { Member, MemberEditForm } from ".";

type MemberAction = 
    { type: 'create' , id: number, form: MemberEditForm } 
    | { type: 'update' , id: number, form: MemberEditForm }  
    | { type: 'delete' , id: number } 

export function memberReducer(state : Member[], action : MemberAction) {
    switch(action.type) {
    case "create":
        return [...state, {...action.form, id: action.id}]
    case "update": 
        return state.map(a => a.id == action.id ? {...a, ...action.form} : a)
    case "delete":
        return state.filter(a => a.id != action.id)
    }
}
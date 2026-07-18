export type MenuType = 
    'Welcome' | 'State' | 'Interaction' | 'Effect' | 'Reference' | 'Reducer' | 'Context'

export interface MenuAction {
    (menu:MenuType) : void
}

export interface Member {
    id: number
    name: string
    phone: string
    email: string
}

export type MemberEditForm = Omit<Member, 'id'>
import 'server-only'

import { readFile, writeFile } from "fs/promises"
import path from "path"
import { LoginResult, User } from '../schema/auth-schema'

export async function login(email: string, password: string):Promise<LoginResult> {
    const model = await _read()
    const user = model[email]

    if(!user) {
        throw new Error("Please check your login id.")
    }

    if(user.password !== password) {
        throw new Error("Please check your password.")
    }

    return {...user}
}

export async function create(user: User):Promise<LoginResult> {
    const model = await _read()

    if(model[user.email]) {
        throw new Error("Email is already used.")
    }

    model[user.email] = user
    await _write(model)

    return {...user}
}

export async function update(email: string, name: string) {
    const model = await _read()
    const user = model[email]

    if(!user) {
        throw new Error("There is no user for update.")
    }

    model[email] = {...user, name: name}
    await _write(model)
}

type UserModel = {
    [email:string] : User
}

const PATH = path.join(process.cwd(), "data", "users.json")

async function _write(model: UserModel) {
    await writeFile(PATH, JSON.stringify(model), 'utf-8')
}

async function _read():Promise<UserModel> {
    try {
        const contents = await readFile(PATH, 'utf-8')
        return JSON.parse(contents)
    } catch {
        return {}
    }
}
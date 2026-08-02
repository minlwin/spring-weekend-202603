import 'server-only'

import { readFile, writeFile } from "fs/promises"
import path from "path"

const PATH = path.join(process.cwd(), "data", "course.json")

export type Course = {
    id: number
    name: string
    description: string
    hours: number
    fees: number
}

export type CourseForm = Omit<Course, 'id'>

export type CourseModel = {
    readonly lastId: number
    readonly data : Course[]
}

export async function getAll():Promise<Course[]> {
    const model = await _readModel()
    return model.data
}

export async function findById(id: number): Promise<Course | undefined> {
    const model = await _readModel()
    return model.data.filter(item => item.id == id)
        .pop()
}

export async function create(form: CourseForm):Promise<number> {
    const model = await _readModel()
    const lastId = model.lastId + 1
    const courseList:Course[] = [...model.data, {...form, id: lastId}]
    await _saveModel({
        lastId: lastId,
        data: courseList
    })
    return lastId
}

async function _saveModel(model: CourseModel) {
    await writeFile(PATH, JSON.stringify(model), 'utf-8')
}

async function _readModel():Promise<CourseModel> {
    try {
        const contents = await readFile(PATH, 'utf-8')
        return JSON.parse(contents)
    } catch(e) {
        return {
            lastId: 0,
            data: []
        }
    }
}
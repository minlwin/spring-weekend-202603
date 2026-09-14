'use server'

import { setFeature } from "../storage/active-feature"

export async function setActiveFeature(feature: string) {
    await setFeature(feature)
}
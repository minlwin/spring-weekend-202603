'use server'

import * as security from "@/lib/services/security/security-context"

export async function logout() {
    await security.clearContext()
}
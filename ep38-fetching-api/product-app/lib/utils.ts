import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export function url(path: string, params?: {
  [key: string] : any
}) {
  const baseUrl = process.env.BASE_URL

  if(params) {
    var searchParams = new URLSearchParams(params)
    return `${baseUrl}/${path}?${searchParams.toString()}`
  }

  return `${baseUrl}/${path}`
}
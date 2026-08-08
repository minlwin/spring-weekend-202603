import { toast } from "@/components/ui/toast"
import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export async function handle(action : () => Promise<void>) {
  try {
    await action()
  } catch(e) {
    const error = e as Error
    toast.add({
      title : "Error",
      description: error.message
    })
  }
}
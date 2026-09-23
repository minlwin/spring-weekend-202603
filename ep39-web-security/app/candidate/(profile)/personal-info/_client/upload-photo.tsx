'use client'

import { Button } from "@/components/ui/button"
import { Camera } from "lucide-react"
import { useRef } from "react"
import * as actions from "@/lib/services/actions/candidate/profile.action"
import { safeCall } from "@/lib/utils";

export default function UploadPhotoComponent({id} : {id : any}) {

    const fileInput = useRef<HTMLInputElement | null>(null)

    function uploadPhoto() {
        const fileList = fileInput.current?.files
        if(fileList && fileList.length > 0) {
            safeCall(async () => {
                actions.uploadPhoto(id, fileList[0])
            })
        }
    }

    return (
        <>
            <Button type="button" className='w-full' onClick={() => fileInput.current?.click()}>
                <Camera /> Upload Photo
            </Button>
            <form className="hidden">
                <input type="file" name="file" ref={fileInput} onChange={uploadPhoto} />
            </form>
        </>
    )
}
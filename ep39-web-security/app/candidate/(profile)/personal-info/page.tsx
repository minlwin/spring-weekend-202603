import PageTemplate from "@/components/commons/page-template";
import { Button } from "@/components/ui/button";
import { Item, ItemContent, ItemDescription, ItemTitle } from "@/components/ui/item";
import { getLoginUser } from "@/lib/services/storage/security-context";
import { Camera, Pencil, User } from "lucide-react";
import * as actions from "@/lib/services/actions/candidate/profile.action"
import { redirect } from "next/navigation";
import { formatDateTime, resources } from "@/lib/utils";
import Link from "next/link";
import UploadPhotoComponent from "./_client/upload-photo";
import Image from "next/image";

export default async function PersonalInfoPage() {

    const loginUser = await getLoginUser()

    if(!loginUser) {
        // Redirect to Sign In Page
        redirect("/signin?message=You have to login for this operation.")
    }

    const personalInfo = await actions.getProfile(loginUser.email)
    
    let profileImage = undefined
    let salary = undefined

    if(personalInfo.profileImage) {
        profileImage = resources(`profile/${personalInfo.profileImage}`)
    }

    if(personalInfo.expectedSalaryFrom && personalInfo.expectedSalaryTo) {
        salary = `${personalInfo.expectedSalaryFrom.toLocaleString()} to ${personalInfo.expectedSalaryTo.toLocaleString()}`
    } else if (personalInfo.expectedSalaryFrom && !personalInfo.expectedSalaryTo) {
        salary = personalInfo.expectedSalaryFrom.toLocaleString()
    } else if (!personalInfo.expectedSalaryFrom && personalInfo.expectedSalaryTo) {
        salary = personalInfo.expectedSalaryTo.toLocaleString()
    }

    return (
        <PageTemplate title="Personal Information">
            <div className="flex gap-4">

                <section className="flex-1 space-y-3">
                    {/* Profile Photo */}
                    <ProfileImage url={profileImage} />

                    <div>
                        <UploadPhotoComponent id={personalInfo.id} />
                        <Button render={<Link href={'/candidate/personal-info/edit'} />} 
                            nativeButton={false} className='w-full' variant={'destructive'}>
                            <Pencil /> Edit Information
                        </Button>
                    </div>
                </section>

                <section className="flex-4 grid grid-cols-3 gap-4">
                    {/* Personal Information */}
                    <Information label="Name" value={personalInfo.name} />
                    <Information label="Date Of Birth" value={getValue(personalInfo.dob)} className="col-start-1" />
                    <Information label="Gender" value={getValue(personalInfo.gender)} />

                    <Information label="Phone" value={getValue(personalInfo.phone)} className="col-start-1" />
                    <Information label="Email" value={personalInfo.email} />

                    <Information label="Job Title" value={getValue(personalInfo.jobTitle)} className="col-start-1" />
                    <Information label="Status" value={getValue(personalInfo.statusValue)} />
                    <Information label="Expected Salary" value={getValue(salary)} />

                    <Information label="Biography" value={getValue(personalInfo.biography)} className="col-span-3" />

                    <Information label="Registered At" value={formatDateTime(personalInfo.registeredAt)} className="col-start-1" />
                    <Information label="Verified At" value={formatDateTime(personalInfo.activatedAt)} />
                
                </section>

            </div>
        </PageTemplate>
    )
}

type InformationProps = {
    label: string
    value: string
    className?: string
}

function Information({label, value, className} : InformationProps) {
    return (
        <Item variant={'outline'} className={className}>
            <ItemContent>
                <ItemTitle>{label}</ItemTitle>
                <ItemDescription>{value}</ItemDescription>
            </ItemContent>
        </Item>
    )
}

function ProfileImage({url} : {url?: string}) {

    if(!url) {
        return (
            <ProfileImageDefault />
        )
    }

    return (
        <div>
            <img src={url} alt="Profile Image" />
        </div>
    )
}

function ProfileImageDefault() {
    return (
        <section className="flex flex-col items-center justify-center gap-2 border rounded-lg h-60">
            <User size={120} color="#2A6B5C" />
            <h5 className="text-xl text-gray-500 font-semibold">Profile Photo</h5>
        </section>
    )
}

function getValue(value?: string) {
    return value || "Undefined"
}
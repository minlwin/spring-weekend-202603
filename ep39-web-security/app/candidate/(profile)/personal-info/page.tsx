import PageTemplate from "@/components/commons/page-template";
import { Button } from "@/components/ui/button";
import { Item, ItemContent, ItemDescription, ItemTitle } from "@/components/ui/item";
import { Camera, Pencil, User } from "lucide-react";

export default function PersonalInfoPage() {
    return (
        <PageTemplate title="Personal Information">
            <div className="flex gap-4">

                <section className="flex-1 space-y-3">
                    {/* Profile Photo */}
                    <ProfileImage />

                    <div>
                        <Button className='w-full'>
                            <Camera /> Upload Photo
                        </Button>
                        <Button className='w-full' variant={'destructive'}>
                            <Pencil /> Edit Profile
                        </Button>
                    </div>
                </section>

                <section className="flex-4 grid grid-cols-3 gap-4">
                    {/* Personal Information */}
                    <Information label="Name" value="David Lah" />
                    <Information label="Date Of Birth" value="David Lah" className="col-start-1" />
                    <Information label="Gender" value="David Lah" />

                    <Information label="Phone" value="David Lah" className="col-start-1" />
                    <Information label="Email" value="David Lah" />

                    <Information label="Job Title" value="David Lah" className="col-start-1" />
                    <Information label="Expected Salary" value="David Lah" />
                    <Information label="Status" value="David Lah" />

                    <Information label="Biography" value="David Lah" className="col-span-3" />

                    <Information label="Registered At" value="David Lah" className="col-start-1" />
                    <Information label="Verified At" value="David Lah" />
                
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
        <></>
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
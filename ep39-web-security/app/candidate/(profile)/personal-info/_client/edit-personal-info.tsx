'use client'

import { FormsInput, FormsSelect } from "@/components/forms";
import FormsTexarea from "@/components/forms/forms-textarea";
import { Button } from "@/components/ui/button";
import { PersonalInfoForm, PersonalInfoFormSchema, PersonalInformation } from "@/lib/types";
import { zodResolver } from "@hookform/resolvers/zod";
import { Save } from "lucide-react";
import { useForm } from "react-hook-form";
import * as actions from "@/lib/services/actions/candidate/profile.action"
import { safeCall } from "@/lib/utils";

export default function EditPersonalInfoComponent({info} : {info : PersonalInformation}) {

    const form = useForm({
        resolver: zodResolver(PersonalInfoFormSchema),
        defaultValues: {
            name: info.name,
            dob: info.dob || '',
            gender: info.gender || '',
            phone: info.phone || '',
            email: info.email,
            jobTitle: info.jobTitle || '',
            expectedSalaryFrom: info.expectedSalaryFrom?.toString() || '',
            expectedSalaryTo: info.expectedSalaryTo?.toString() || '',
            biography: info.biography || '',
            status: info.status || ''
        }
    })

    function save(form : PersonalInfoForm) {
        console.log(form)
        safeCall(async () => {
            await actions.update(info.id, form)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(save)} className="grid grid-cols-4 gap-4">
            <FormsInput control={form.control} name="name" label="Name" />
            <FormsInput control={form.control} name="dob" label="Date of Birth" type="date" className="col-start-1" />
            <FormsSelect control={form.control} name="gender" label="Gender" options={[
                {label : "Select One", value : ""},
                {label : "Male", value : "Male"},
                {label : "Female", value : "Female"},
            ]} />
            <FormsInput control={form.control} name="phone" label="Phone Number" type="tel" className="col-start-1" />
            <FormsInput control={form.control} name="email" label="Email" type="email" />

            <FormsInput control={form.control} name="jobTitle" label="Job Title" className="col-start-1" />
            <FormsSelect control={form.control} name="status" label="Status" className="col-start-1" options={[
                {label : 'Select One', value : ''},
                {label : 'Actively Looking', value : 'ActivelyLooking'},
                {label : 'Open Offer', value : 'OpenOffer'},
                {label : 'Not Looking', value : 'NotLooking'},
            ]} />
            <FormsInput control={form.control} name="expectedSalaryFrom" label="Expected Salary From" type="number" />
            <FormsInput control={form.control} name="expectedSalaryTo" label="Expected Salary To" type="number" />

            <FormsTexarea control={form.control} name="biography" label="Biography" className="col-start-1 col-span-4" />


            <nav className="col-start-1 col-span-4">
                <Button type="submit" >
                    <Save /> Save Information
                </Button>
            </nav>
        </form>
    )
}
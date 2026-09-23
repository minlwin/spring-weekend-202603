import { z } from "zod";

export const PersonalInfoFormSchema = z.object({
  name: z.string().nonempty("Please enter your name."),
  dob: z.string().nonempty("Please enter date of birth."),
  gender: z.string().nonempty("Please select gender."),
  phone: z.string().nonempty("Please enter phone number."),
  email: z.string().nonempty("Please enter email address.").email("Please enter a valid email address."),
  jobTitle: z.string().min(1, "Please enter job title."),
  expectedSalaryFrom: z.string(),
  expectedSalaryTo: z.string(),
  status: z.string(),
  biography: z.string(),
});

export type PersonalInfoForm = z.infer<typeof PersonalInfoFormSchema>;

export interface PersonalInformation {
    id: number;
    name: string;
    profileImage: string;
    dob: string; // LocalDate → "yyyy-MM-dd"
    gender: 'Male' | 'Female';
    phone: string;
    email: string;
    jobTitle: string;
    expectedSalaryFrom: number;
    expectedSalaryTo: number;
    status: string;
    statusValue: string;
    biography: string;
    registeredAt: string; // LocalDateTime → ISO 8601 string
    activatedAt: string; // LocalDateTime → ISO 8601 string
}
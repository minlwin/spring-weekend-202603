import { Button } from "@/components/ui/button";
import { Briefcase, Building, LogIn, Network, Power, UserShield } from "lucide-react";
import Link from "next/link";

export default function WelcomePage() {
  return (
    <main className="h-screen flex flex-col items-center justify-center gap-4">
      
      <h1 className="text-4xl font-semibold">Hello JOB</h1>

      <Briefcase size={180}/>

      <header className="text-center w-1/2 space-y-2">
        <h3 className="text-xl font-semibold">Find Your Next Career Move</h3>
        <p className="text-lg">Discover thousands of job opportunities tailored to your skills. Build your profile, upload your resume, and connect with top employers today.</p>
      </header>


      <div className="space-x-1">
        <Button render={<Link href={'/signup/candidate'} />} nativeButton={false}>
          <Power /> Getting Start
        </Button>
        <Button render={<Link href={'/signup/partner'} />} nativeButton={false}>
          <Network /> Employeer Sign Up
        </Button>
        <Button render={<Link href={'/signin'} />} nativeButton={false}>
          <LogIn /> Sign In
        </Button>
      </div>

      <section className="text-center space-y-3 mt-4">
        <h3 className="text-xl font-semibold">Account Activation</h3>
        <p>For first time user, you have to activate your account. <br/>Please check email and finish account activation process.</p>
        <Button render={<Link href={'/activation'}></Link>} nativeButton={false} variant={'outline'}>
          <UserShield /> Account Activation
        </Button>
      </section>
    </main>
  )
}
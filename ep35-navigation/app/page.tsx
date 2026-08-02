import { Button } from "@/components/ui/button";
import { Book, LogIn } from "lucide-react";
import Link from "next/link";

export default function WelcomePage() {
  return (
    <div className="h-screen flex items-center justify-center">
      <section>
        <h1 className="text-4xl font-semibold">Learning Next JS</h1>

        <div className="text-center mt-4 space-x-2">
          <Button nativeButton={false} render={<Link href={'/courses'} />}>
            <Book /> Courses
          </Button>
          <Button nativeButton={false} render={<Link href={'/signin'} />} variant={'outline'}>
            <LogIn /> Sign In
          </Button>
        </div>
      </section>
    </div>
  )
}
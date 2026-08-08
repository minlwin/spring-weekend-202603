import { Button } from "@/components/ui/button";
import { Coffee, LogIn } from "lucide-react";
import Link from "next/link";

export default function Welcome() {

  return (
    <main className="flex items-center justify-center min-h-screen">

      <div className="flex flex-col items-center gap-2">
        <Coffee size={180} />
        <h1 className="text-4xl">Java Developer Class</h1>

        <Button nativeButton={false} render={<Link href='/signin' />}>
          <LogIn /> Sign In
        </Button>
      </div>
    </main>
  )
}
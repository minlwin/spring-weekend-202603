import { Card, CardContent, CardDescription, CardTitle } from "@/components/ui/card";
import PageTitle from "../_commons/page-title";
import { ArrowRight, BookOpenCheck } from "lucide-react";
import { Button } from "@/components/ui/button";


const LIST = [
  {
    id: 1,
    name: "Java Basic",
    hours: 80,
    price: 150000,
    description:
      "Learn Java fundamentals including syntax, OOP, collections, exception handling, file I/O, and modern Java features. Build a strong foundation for enterprise application development.",
  },
  {
    id: 2,
    name: "Java Web",
    hours: 120,
    price: 150000,
    description:
      "Develop dynamic web applications using Servlets, JSP, JDBC, MVC architecture, session management, authentication, and REST APIs while working with relational databases.",
  },
  {
    id: 3,
    name: "Spring MVC",
    hours: 180,
    price: 250000,
    description:
      "Master Spring Framework with Spring Boot, Spring MVC, Spring Data JPA, Spring Security, RESTful APIs, validation, testing, and deployment using industry best practices.",
  },
  {
    id: 4,
    name: "Spring Fullstack with React",
    hours: 180,
    price: 250000,
    description:
      "Build complete full-stack applications using Spring Boot and React. Learn REST APIs, authentication, state management, routing, database integration, and production deployment.",
  },
];

export default function CourseListPage() {
    return (
        <div>
            <PageTitle name="Our Course" />

            <section className="mt-4 grid grid-cols-2 gap-4">
                {LIST.map(item =>
                    <Card key={item.id}>
                        <CardContent className="flex gap-4">
                            <div className="px-8">
                                <BookOpenCheck size={120} />
                            </div>
                            <div>
                                <CardTitle className="text-2xl">{item.name}</CardTitle>
                                <CardDescription>{item.description}</CardDescription>

                                <div className="mt-4">
                                    <Button>
                                        <ArrowRight /> Read More
                                    </Button>
                                </div>
                            </div>
                        </CardContent>
                    </Card>
                )}
            </section>
        </div>
    )
}
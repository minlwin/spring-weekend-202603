import { Card, CardContent } from "../ui/card";

export default function NoData({name} : {name? : string}) {
    return (
        <Card>
            <CardContent className="h-50 flex items-center justify-center">
                There is no {name ?? 'data'}.
            </CardContent>
        </Card>
    )
}
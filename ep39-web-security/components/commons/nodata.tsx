import { AlertTriangle } from "lucide-react";
import { Item, ItemContent, ItemDescription, ItemTitle } from "../ui/item";

export default function NoData({name} : {name : string}) {
    return (
        <Item variant={"outline"}>
            <ItemContent>
                <ItemTitle>
                    <AlertTriangle size={16} /> No Data
                </ItemTitle>
                <ItemDescription>
                    There is no {name} data. Please change search criteria and search again.
                </ItemDescription>
            </ItemContent>
        </Item>
    )
}
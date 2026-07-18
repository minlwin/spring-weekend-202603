import { useReducer } from "react";
import Layout from "../components/Layout";
import { memberReducer } from "../model/members.reducer";

export default function ReducerDemoPage() {

    const [members, dispatch] = useReducer(memberReducer, [])

    return (
        <Layout title="Use Reducer Hook">

        </Layout>
    )
}

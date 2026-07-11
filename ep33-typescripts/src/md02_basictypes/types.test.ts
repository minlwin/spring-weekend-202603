import { describe, expect, test } from "vitest";
import { checkUser } from "./types.js";

describe("Interface Test", () => {

    test("Interface is shape of an object", () => {

        let param:any = {
            name: "Thidar",
            age: 20,
            job: "Student",
            status: "Single"
        }

        expect(checkUser(param)).toBe("Thidar is 20 years old.")
    })
})
import { describe, expect, test } from "vitest";
import { check } from "./truthly_falsly.js";

describe("Truthly & Falsly Test", () => {

    test("Numbers", () => {
        expect(check(0)).toBe("Result is FALSE")
        expect(check(1)).toBe("Result is TRUE")
        expect(check(-1)).toBe("Result is TRUE")
    })

    test("String", () => {
        expect(check("")).toBe("Result is FALSE")
        expect(check(" ")).toBe("Result is TRUE")
    })

    test("Special Types", () => {
        expect(check(null)).toBe("Result is FALSE")
        expect(check(undefined)).toBe("Result is FALSE")

        let value:any 
        expect(check(value)).toBe("Result is FALSE")

        value = 10
        expect(check(value)).toBe("Result is TRUE")

        let other:unknown
        expect(check(other)).toBe("Result is FALSE")

        other = 100
        expect(check(other)).toBe("Result is TRUE")
    })

})
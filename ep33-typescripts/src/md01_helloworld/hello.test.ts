import { describe, test, expect } from 'vitest';
import { hello } from './hello.js';

describe("My First TypeScript Test", () => {

    test("Hello Test", () => {
        expect(hello("JDC")).toBe("Hello JDC!")
    })

    test("Plus Test", () => expect(1 + 3).toBe(4))
})
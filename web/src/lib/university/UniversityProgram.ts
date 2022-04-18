import type { Program } from "$lib/program/Program"
import type { University } from "$lib/university/University"

export class UniversityProgram {
    readonly university: University
    readonly program: Program
}

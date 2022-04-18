import { Program } from "$lib/program/Program"
import { University } from "$lib/university/University"
import { Type } from "class-transformer"

export class UniversityProgram {
    @Type(() => University)
    readonly university: University

    @Type(() => Program)
    readonly program: Program
}

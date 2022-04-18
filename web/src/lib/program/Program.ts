import { Expose } from "class-transformer"

export class Program {
    @Expose({ name: "_id" })
    readonly id: string
    readonly name: string
}

import { Expose } from "class-transformer"

export class University {
    @Expose({ name: "_id" })
    readonly id: string
    readonly name: string
    readonly logoUrl: string
}

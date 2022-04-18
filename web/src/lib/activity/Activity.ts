import { Expose } from "class-transformer"

export class Activity {
    @Expose({ name: "_id" })
    readonly id: string
    readonly name: string
    readonly parentId: string
}

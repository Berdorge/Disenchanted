import { Activity } from "$lib/activity/Activity"
import { Expose, Type } from "class-transformer"

export class ChildActivity {
    @Expose({ name: "_id" })
    readonly id: string

    readonly name: string

    readonly logoUrl: string

    @Type(() => Activity)
    readonly parent: Activity | null
}

import { Description } from "$lib/description/Description"
import { Expose, Type } from "class-transformer"

export class DescribedActivity {
    @Expose({ name: "_id" })
    readonly id: string

    readonly name: string

    readonly logoUrl: string | null

    @Type(() => Description)
    readonly descriptions: Array<Description>
}

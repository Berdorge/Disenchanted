import { Expose } from "class-transformer"

export class SignUpResponse {
    @Expose({ name: "_id" })
    readonly id: string = ""
    readonly username: string = ""
    readonly jwt: string = ""
}

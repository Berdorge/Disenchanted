import { Expose } from "class-transformer"

export class SignInResponse {
    @Expose({ name: "_id" })
    readonly id: string = ""
    readonly username: string = ""
    readonly jwt: string = ""
}

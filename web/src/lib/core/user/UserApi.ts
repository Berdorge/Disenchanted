import { HttpClientToken } from "$lib/core/network/HttpClient"
import type { User } from "$lib/core/user/User"
import type { AxiosInstance } from "axios"
import { StatusCodes } from "http-status-codes"
import { inject, singleton } from "tsyringe"

@singleton()
export class UserApi {
    private readonly client: AxiosInstance

    constructor(@inject(HttpClientToken) client: AxiosInstance) {
        this.client = client
    }

    getMe = (jwt: string): Promise<User | undefined> =>
        this.client
            .get("users/me", {
                headers: {
                    Authorization: `Bearer ${jwt}`
                }
            })
            .then((something) => ({
                ...something.data,
                id: something.data._id
            }))
            .catch((e) => {
                console.trace(e.message)
                return undefined
            })

    doesUserExist = (username: string): Promise<boolean> =>
        this.client
            .get(`users/${username}`)
            .then((something) => {
                console.log(something)
                return true
            })
            .catch((e) => {
                if (e.response && e.response.status == StatusCodes.NOT_FOUND) {
                    return false
                } else {
                    throw e
                }
            })
}

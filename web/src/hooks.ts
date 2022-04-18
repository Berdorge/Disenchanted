import { COOKIE_JWT } from "$lib/core/cookies"
import { UserApi } from "$lib/user/UserApi"
import type { GetSession, Handle } from "@sveltejs/kit"
import { parse } from "cookie"
import { container } from "tsyringe"

export const handle: Handle = ({ event, resolve }) => {
    const cookies = parse(event.request.headers.get("cookie") || "")

    if (cookies[COOKIE_JWT]) {
        return container
            .resolve(UserApi)
            .getMe(cookies[COOKIE_JWT])
            .then((user) => (event.locals.user = user))
            .then(() => resolve(event))
    } else {
        return resolve(event)
    }
}

export const getSession: GetSession = (event) => ({
    user: event.locals.user
})

import type { AxiosInstance } from "axios"
import axios from "axios"
import "reflect-metadata"
import { container, instanceCachingFactory } from "tsyringe"

export const HttpClientToken = "AxiosInstance"

container.register<AxiosInstance>(HttpClientToken, {
    useFactory: instanceCachingFactory(() =>
        axios.create({ baseURL: import.meta.env.VITE_SERVER_URL })
    )
})

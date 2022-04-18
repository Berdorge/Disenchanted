import { HttpClientToken } from "$lib/network/HttpClient"
import { University } from "$lib/university/University"
import type { AxiosInstance } from "axios"
import { plainToInstance } from "class-transformer"
import { inject, singleton } from "tsyringe"

@singleton()
export class UniversityApi {
    private readonly client: AxiosInstance

    constructor(@inject(HttpClientToken) client: AxiosInstance) {
        this.client = client
    }

    getUniversities = (): Promise<Array<University>> =>
        this.client
            .get("universities")
            .then((response) => (Array.isArray(response.data) ? response.data : [response.data]))
            .then((universities) => plainToInstance(University, universities))

    getUniversity = (universityId: string): Promise<University | null> =>
        this.client
            .get(`universities/${universityId}`)
            .then((response) => plainToInstance(University, response.data))
            .catch((e) => {
                console.error(e)
                return null
            })
}

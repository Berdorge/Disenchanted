import { Activity } from "$lib/activity/Activity"
import { HttpClientToken } from "$lib/network/HttpClient"
import type { AxiosInstance } from "axios"
import { plainToInstance } from "class-transformer"
import { inject, singleton } from "tsyringe"

@singleton()
export class UniversityProgramActivityApi {
    private readonly client: AxiosInstance

    constructor(@inject(HttpClientToken) client: AxiosInstance) {
        this.client = client
    }

    getUniversityActivities = (universityId: string): Promise<Array<Activity>> =>
        this.client
            .get(`universities/${universityId}/activities`)
            .then((response) => (Array.isArray(response.data) ? response.data : [response.data]))
            .then((activities) => plainToInstance(Activity, activities))
}

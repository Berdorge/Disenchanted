import { Activity } from "$lib/activity/Activity"
import { HttpClientToken } from "$lib/network/HttpClient"
import type { AxiosInstance } from "axios"
import { plainToInstance } from "class-transformer"
import { inject, singleton } from "tsyringe"

@singleton()
export class ActivityApi {
    private readonly client: AxiosInstance

    constructor(@inject(HttpClientToken) client: AxiosInstance) {
        this.client = client
    }

    getActivities = (): Promise<Array<Activity>> =>
        this.client
            .get("activities")
            .then((response) => (Array.isArray(response.data) ? response.data : [response.data]))
            .then((activities) => plainToInstance(Activity, activities))

    getChildActivities = (parentId: string): Promise<Array<Activity>> =>
        this.client
            .get(`activities/${parentId}/children`)
            .then((response) => (Array.isArray(response.data) ? response.data : [response.data]))
            .then((activities) => plainToInstance(Activity, activities))
}

import { Activity } from "$lib/activity/Activity"
import { ChildActivity } from "$lib/activity/ChildActivity"
import { HttpClientToken } from "$lib/network/HttpClient"
import { wrapResponseDataToArray } from "$lib/utils/Response"
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
            .then(wrapResponseDataToArray)
            .then((activities) => plainToInstance(Activity, activities))

    getActivity = (activityId: string): Promise<ChildActivity | null> =>
        this.client
            .get(`activities/${activityId}`)
            .then((response) => plainToInstance(ChildActivity, response.data))
            .catch((e) => {
                console.log(e)
                return null
            })

    getChildActivities = (parentId: string): Promise<Array<Activity>> =>
        this.client
            .get(`activities/${parentId}/children`)
            .then(wrapResponseDataToArray)
            .then((activities) => plainToInstance(Activity, activities))
}

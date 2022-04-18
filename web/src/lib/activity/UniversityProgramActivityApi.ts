import { DescribedActivity } from "$lib/activity/DescribedActivity"
import { HttpClientToken } from "$lib/network/HttpClient"
import { University } from "$lib/university/University"
import { wrapResponseDataToArray } from "$lib/utils/Response"
import type { AxiosInstance } from "axios"
import { plainToInstance } from "class-transformer"
import { inject, singleton } from "tsyringe"

@singleton()
export class UniversityProgramActivityApi {
    private readonly client: AxiosInstance

    constructor(@inject(HttpClientToken) client: AxiosInstance) {
        this.client = client
    }

    getActivityUniversities = (activityId: string): Promise<Array<University>> =>
        this.client
            .get(`activities/${activityId}/universities`)
            .then(wrapResponseDataToArray)
            .then((universities) => plainToInstance(University, universities))

    getUniversityActivities = (universityId: string): Promise<Array<DescribedActivity>> =>
        this.client
            .get(`universities/${universityId}/activities`)
            .then(wrapResponseDataToArray)
            .then((activities) => plainToInstance(DescribedActivity, activities))

    getUniversityProgramActivities = (
        universityId: string,
        programId: string
    ): Promise<Array<DescribedActivity>> =>
        this.client
            .get(`universities/${universityId}/programs/${programId}/activities`)
            .then(wrapResponseDataToArray)
            .then((activities) => plainToInstance(DescribedActivity, activities))
}

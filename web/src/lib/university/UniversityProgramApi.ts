import { HttpClientToken } from "$lib/network/HttpClient"
import { Program } from "$lib/program/Program"
import { UniversityProgram } from "$lib/university/UniversityProgram"
import { wrapResponseDataToArray } from "$lib/utils/Response"
import type { AxiosInstance } from "axios"
import { plainToInstance } from "class-transformer"
import { inject, singleton } from "tsyringe"

@singleton()
export class UniversityProgramApi {
    private readonly client: AxiosInstance

    constructor(@inject(HttpClientToken) client: AxiosInstance) {
        this.client = client
    }

    getPrograms = (universityId: string): Promise<Array<Program>> =>
        this.client
            .get(`universities/${universityId}/programs`)
            .then(wrapResponseDataToArray)
            .then((programs) => plainToInstance(Program, programs))

    getUniversityProgram = (
        universityId: string,
        programId: string
    ): Promise<UniversityProgram | null> =>
        this.client
            .get(`universities/${universityId}/programs/${programId}`)
            .then((response) => plainToInstance(UniversityProgram, response.data))
            .catch((e) => {
                console.error(e)
                return null
            })
}

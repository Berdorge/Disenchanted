import { wrapToArray } from "$lib/utils/Array"
import type { AxiosResponse } from "axios"

export const wrapResponseDataToArray = (response: AxiosResponse) => wrapToArray(response.data)

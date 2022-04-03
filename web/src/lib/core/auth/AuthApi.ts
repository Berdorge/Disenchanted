import type { SignInBody } from "$lib/core/auth/SignInBody"
import { SignInResponse } from "$lib/core/auth/SignInResponse"
import type { SignUpBody } from "$lib/core/auth/SignUpBody"
import { SignUpResponse } from "$lib/core/auth/SignUpResponse"
import { HttpClientToken } from "$lib/core/network/HttpClient"
import type { AxiosInstance } from "axios"
import { plainToInstance } from "class-transformer"
import { StatusCodes } from "http-status-codes"
import { inject, singleton } from "tsyringe"

@singleton()
export class AuthApi {
    private readonly client: AxiosInstance

    constructor(@inject(HttpClientToken) client: AxiosInstance) {
        this.client = client
    }

    signIn = (body: SignInBody): Promise<SignInResponse> =>
        this.client
            .post("signIn", body)
            .then((something) => plainToInstance(SignInResponse, something.data))
            .catch((error) => {
                if (error.response && error.response.status == StatusCodes.UNAUTHORIZED) {
                    throw "Ваши логин или пароль неправильные."
                } else {
                    throw "Что-то пошло не так."
                }
            })

    signUp = (body: SignUpBody): Promise<SignUpResponse> =>
        this.client
            .post("signUp", body)
            .then((something) => plainToInstance(SignUpResponse, something.data))
            .catch((error) => {
                if (error.response && error.resopnse.status == StatusCodes.CONFLICT) {
                    throw "Пользователь с таким логином уже существует. Пожалуйста, вернитесь назад и введите новый логин."
                } else {
                    throw "Что-то пошло не так."
                }
            })
}

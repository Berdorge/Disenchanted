package ru.disenchanted.backend.presentation.user

import ru.disenchanted.backend.domain.user.User

fun User.withJwt(jwt: String) = ExposableUserWithJwt(
    id = id,
    username = username,
    jwt = jwt
)

fun User.asExposable() = ExposableUser(
    id = id,
    username = username
)

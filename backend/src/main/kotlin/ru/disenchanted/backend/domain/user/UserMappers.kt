package ru.disenchanted.backend.domain.user

fun User.withJwt(jwt: String) = ExposableUserWithJwt(
    id = id,
    username = username,
    jwt = jwt
)

fun User.asExposable() = ExposableUser(
    id = id,
    username = username
)

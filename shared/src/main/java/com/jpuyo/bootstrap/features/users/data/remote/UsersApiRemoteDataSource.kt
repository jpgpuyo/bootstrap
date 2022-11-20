package com.jpuyo.bootstrap.features.users.data.remote

import UsersRemoteDataSource
import com.jpuyo.bootstrap.common.datasource.remote.withPath
import com.jpuyo.bootstrap.domain.model.DomainError
import com.jpuyo.bootstrap.domain.model.Either
import com.jpuyo.bootstrap.features.users.data.dto.UserDetailResponseDto
import com.jpuyo.bootstrap.features.users.data.dto.UsersResponseDto
import com.jpuyo.bootstrap.features.users.domain.model.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import java.net.UnknownHostException

class UsersApiRemoteDataSource(private val client: HttpClient) : UsersRemoteDataSource {
    companion object {
        const val ENDPOINT = "https://reqres.in"
    }
    override suspend fun getUsers(): Either<DomainError, List<User>> =
        execute<UsersResponseDto> {
            client.get {
                url.withPath("/api/users")
            }.body()
        }.map { it.toUsersList() }

    override suspend fun getUserDetail(id: String): Either<DomainError, User> =
        execute<UserDetailResponseDto> {
            client.get {
                url.withPath("/api/users/$id")
            }.body()
        }.map { it.toUser() }

    private suspend fun <R> execute(f: suspend () -> R): Either<DomainError, R> =
        try {
            Either.Right(f())
        } catch (e: Throwable) {
            print(e.toString())
            Either.Left(
                when (e) {
                    is ClientRequestException -> when (e.response.status) {
                        HttpStatusCode.Unauthorized -> DomainError.InvalidCredentials
                        HttpStatusCode.NotFound -> DomainError.NotFound
                        HttpStatusCode.BadRequest -> DomainError.NoInternet
                        else -> DomainError.HttpStatusCodeNotHandled(e.message)
                    }
                    is UnknownHostException -> DomainError.NoInternet
                    else -> DomainError.HttpErrorNotHandled(e.message ?: "")
                }
            )
        }
}

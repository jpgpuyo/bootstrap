package com.jpuyo.bootstrap.features.users.data

import UsersRemoteDataSource
import UsersRepository
import com.jpuyo.bootstrap.common.datasource.preferences.Preferences
import com.jpuyo.bootstrap.domain.model.DomainError
import com.jpuyo.bootstrap.domain.model.Either
import com.jpuyo.bootstrap.features.users.domain.model.User

class UsersRepositoryImpl(
    private val remote: UsersRemoteDataSource,
    private val preferences: Preferences
) :
    UsersRepository {

    override suspend fun getUsers(): Either<DomainError, List<User>> {
        return remote.getUsers()
    }

    override suspend fun getUserDetail(id: String): Either<DomainError, User> {
        return remote.getUserDetail(id)
    }
}
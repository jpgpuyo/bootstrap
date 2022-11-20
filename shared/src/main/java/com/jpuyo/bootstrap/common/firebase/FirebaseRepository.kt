package com.jpuyo.bootstrap.common.firebase

interface FirebaseRepository {
    suspend fun getToken(): String
}

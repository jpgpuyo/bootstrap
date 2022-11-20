package com.jpuyo.bootstrap.common.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseRemoteDataSource : FirebaseRepository {

    override suspend fun getToken(): String {
        return suspendCoroutine { continuation ->
            Firebase.messaging.token
                .addOnCompleteListener {
                    continuation.resume(it.result!!)
                }
        }
    }
}
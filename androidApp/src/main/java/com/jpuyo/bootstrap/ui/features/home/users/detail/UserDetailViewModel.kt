package com.jpuyo.bootstrap.ui.features.home.users.detail

import UsersRepository
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.jpuyo.bootstrap.core.ui.RootViewModel
import com.jpuyo.bootstrap.error.ErrorHandler
import com.jpuyo.bootstrap.executor.Executor
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val repository: UsersRepository,
    executor: Executor,
    errorHandler: ErrorHandler
) :
    RootViewModel(executor, errorHandler) {

    val userDetailState: MutableState<UserDetailState> = mutableStateOf(UserDetailState())

    fun onTriggerEvent(event: UserDetailEvents) {
        when (event) {
            is UserDetailEvents.GetUserDetail -> getUserDetail(event.userId)
        }
    }

    private fun getUserDetail(userId: String) {
        viewModelScope.launch {
            execute {
                repository.getUserDetail(userId)
            }.fold(
                error = {
                        userDetailState.value = userDetailState.value.copy(errorMessage = getErrorMessage(it))
                },
                success = {
                    userDetailState.value = userDetailState.value.copy(user = it, errorMessage = "")
                }
            )
        }
    }
}

sealed class UserDetailEvents {
    data class GetUserDetail(val userId: String) : UserDetailEvents()
}
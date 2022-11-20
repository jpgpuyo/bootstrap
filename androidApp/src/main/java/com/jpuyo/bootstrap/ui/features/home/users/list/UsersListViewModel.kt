package com.jpuyo.bootstrap.ui.features.home.users.list

import UsersRepository
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.jpuyo.bootstrap.core.ui.RootViewModel
import com.jpuyo.bootstrap.error.ErrorHandler
import com.jpuyo.bootstrap.executor.Executor
import kotlinx.coroutines.launch

class UsersListViewModel(
    private val usersRepository: UsersRepository,
    executor: Executor,
    errorHandler: ErrorHandler
) : RootViewModel(executor, errorHandler) {

    val state: MutableState<UsersListState> = mutableStateOf(UsersListState(isRefreshing = false, isLoading = true, errorMessage = ""))

    fun onTriggerEvent(event: UserListEvents) {
        when (event) {
            UserListEvents.GetUsers -> getUsers()
        }
    }

    private fun getUsers() {
        state.value = state.value.copy(isRefreshing = false, isLoading = true, errorMessage = "")
        viewModelScope.launch {
            execute {
                usersRepository.getUsers()
            }.fold(
                error = {
                    state.value = state.value.copy(isLoading = false, errorMessage = getErrorMessage(it))
                },
                success = {
                    state.value = state.value.copy(
                        users = it,
                        isRefreshing = false,
                        isLoading = false,
                        errorMessage = ""
                    )
                })
        }
    }
}

sealed class UserListEvents {
    object GetUsers : UserListEvents()
}
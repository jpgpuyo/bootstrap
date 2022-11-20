package com.jpuyo.bootstrap.ui.features.home.users.list

import com.jpuyo.bootstrap.features.users.domain.model.User

data class UsersListState(
    val users: List<User> = emptyList(),
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = ""
) {
    val isEmptyView: Boolean
        get() = !isLoading && users.isEmpty()
}
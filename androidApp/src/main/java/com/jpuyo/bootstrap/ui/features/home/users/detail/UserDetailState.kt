package com.jpuyo.bootstrap.ui.features.home.users.detail

import com.jpuyo.bootstrap.features.users.domain.model.User

data class UserDetailState(
    val user: User = User("", "", "", ""),
    val errorMessage: String = ""
)
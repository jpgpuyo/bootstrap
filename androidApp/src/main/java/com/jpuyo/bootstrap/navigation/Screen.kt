package com.jpuyo.bootstrap.navigation

sealed class HomeRoutes(val routeName: String){
    object UserList: HomeRoutes("userList")
    object UserDetail: HomeRoutes("userDetail")

    companion object {
        const val USER_ID = "userId"
    }
}
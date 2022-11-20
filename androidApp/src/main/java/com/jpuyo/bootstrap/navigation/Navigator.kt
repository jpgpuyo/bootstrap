package com.jpuyo.bootstrap.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jpuyo.bootstrap.ui.features.home.users.detail.UserDetailScreen
import com.jpuyo.bootstrap.ui.features.home.users.detail.UserDetailViewModel
import com.jpuyo.bootstrap.ui.features.home.users.list.UserListScreen
import com.jpuyo.bootstrap.ui.features.home.users.list.UsersListViewModel
import org.koin.androidx.compose.getViewModel

class Navigator {

    @ExperimentalComposeUiApi
    @Composable
    fun HomeNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HomeRoutes.UserList.routeName) {
            composable(route = HomeRoutes.UserList.routeName) {
                val viewModel = getViewModel<UsersListViewModel>()
                UserListScreen(
                    state = viewModel.state.value,
                    onTriggerEvent = viewModel::onTriggerEvent,
                    onUserClicked = { userId ->
                        navController.navigate(
                            HomeRoutes.UserDetail.routeName.plus(
                                "/$userId"
                            )
                        )
                    }
                )
            }
            composable(route = HomeRoutes.UserDetail.routeName.plus("/{${HomeRoutes.USER_ID}}")) { entry ->
                val viewModel = getViewModel<UserDetailViewModel>()
                val userId = entry.arguments?.getString(HomeRoutes.USER_ID)
                    UserDetailScreen(
                        userId = userId,
                        userDetailState = viewModel.userDetailState.value,
                        onTriggerEvent = viewModel::onTriggerEvent,
                        onBackClicked = { navController.popBackStack() }
                    )
            }
        }
    }
}
package com.jpuyo.bootstrap.core.di

import android.content.Context
import com.jpuyo.bootstrap.di.*
import com.jpuyo.bootstrap.error.ErrorHandler
import com.jpuyo.bootstrap.executor.AndroidExecutor
import com.jpuyo.bootstrap.executor.Executor
import com.jpuyo.bootstrap.navigation.Navigator
import com.jpuyo.bootstrap.ui.error.AndroidErrorHandler
import com.jpuyo.bootstrap.ui.features.home.users.detail.UserDetailViewModel
import com.jpuyo.bootstrap.ui.features.home.users.list.UsersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun flavorModules(context: Context, flavor: String): List<Module> {
    return when (flavor) {
        "mock" -> listOf(appModule).plus(SharedModule.mockModules(context))
        else -> listOf(appModule).plus(SharedModule.devModules(context))
    }
}

val appModule = module {
    single<Executor> { AndroidExecutor() }
    single<ErrorHandler> { AndroidErrorHandler(get()) }
    single { Navigator() }
    viewModel { UsersListViewModel(get(), get(), get()) }
    viewModel { UserDetailViewModel(get(), get(), get()) }
}
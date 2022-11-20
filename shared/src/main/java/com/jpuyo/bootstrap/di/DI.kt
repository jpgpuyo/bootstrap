package com.jpuyo.bootstrap.di

import UsersRemoteDataSource
import UsersRepository
import android.content.Context
import com.jpuyo.bootstrap.common.datasource.preferences.CommonPreferences
import com.jpuyo.bootstrap.common.datasource.preferences.Preferences
import com.jpuyo.bootstrap.common.datasource.remote.buildClient
import com.jpuyo.bootstrap.common.datasource.remote.buildMockClient
import com.jpuyo.bootstrap.common.firebase.FirebaseRemoteDataSource
import com.jpuyo.bootstrap.common.firebase.FirebaseRepository
import com.jpuyo.bootstrap.common.framework.AndroidFrameworkProvider
import com.jpuyo.bootstrap.common.framework.FrameworkProvider
import com.jpuyo.bootstrap.features.users.data.UsersRepositoryImpl
import com.jpuyo.bootstrap.features.users.data.remote.UsersApiRemoteDataSource
import com.russhwolf.settings.AndroidSettings
import org.koin.core.module.Module
import org.koin.dsl.module

object SharedModule {

    fun mockModules(context: Context): List<Module> = listOf(dataModule(context), mockModule)

    fun devModules(context: Context): List<Module> = listOf(dataModule(context), remoteModule)

    private fun dataModule(context: Context) = module {
        single<UsersRepository> {
            UsersRepositoryImpl(get(), get())
        }
        single<Preferences> {
            CommonPreferences(
                settings = AndroidSettings(
                    context.getSharedPreferences(
                        "bd",
                        Context.MODE_PRIVATE
                    )
                )
            )
        }
        single<FrameworkProvider> { AndroidFrameworkProvider() }
    }

    private val remoteModule = module {
        single<UsersRemoteDataSource> {
            UsersApiRemoteDataSource(
                client = buildClient(endpoint = UsersApiRemoteDataSource.ENDPOINT, isDebug = true)
            )
        }
        single<FirebaseRepository> { FirebaseRemoteDataSource() }
    }

    private val mockModule = module {
        single<UsersRemoteDataSource> {
            UsersApiRemoteDataSource(
                client = buildMockClient(endpoint = UsersApiRemoteDataSource.ENDPOINT, isDebug = true)
            )
        }
        single<FirebaseRepository> { FirebaseRemoteDataSource() }
    }

}
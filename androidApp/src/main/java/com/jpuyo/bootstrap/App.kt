package com.jpuyo.bootstrap

import android.app.Application
import com.jpuyo.bootstrap.core.di.flavorModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                flavorModules(this@App, BuildConfig.FLAVOR)
            )
        }
    }

}
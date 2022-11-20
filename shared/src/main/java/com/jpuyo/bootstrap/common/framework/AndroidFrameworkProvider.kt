package com.jpuyo.bootstrap.common.framework

import android.os.Build
import java.util.*

interface FrameworkProvider {
    fun getRandomUUID(): String
    fun getDeviceDescription(): String
}

class AndroidFrameworkProvider : FrameworkProvider {

    override fun getRandomUUID(): String = UUID.randomUUID().toString()

    override fun getDeviceDescription(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model.replaceFirstChar(Char::uppercase)
        } else manufacturer.replaceFirstChar(Char::uppercase) + " " + model
    }
}
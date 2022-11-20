package com.jpuyo.bootstrap.common.datasource.preferences

import com.russhwolf.settings.Settings

class CommonPreferences(private val settings: Settings) : Preferences {

    companion object {
        private const val DEVICE_ID_KEY = "DEVICE_ID_KEY"
        private const val EMPTY_STRING = ""
    }

    override fun saveDeviceId(deviceId: String) {
        settings.putString(DEVICE_ID_KEY, deviceId)
    }

    override fun getDeviceId(): String = settings.getString(DEVICE_ID_KEY, EMPTY_STRING)

    override fun hasDeviceId(): Boolean =
        settings.hasKey(DEVICE_ID_KEY) && settings.getString(DEVICE_ID_KEY) != EMPTY_STRING

}

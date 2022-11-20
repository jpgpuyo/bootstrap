package com.jpuyo.bootstrap.common.datasource.preferences

interface Preferences {
    fun saveDeviceId(deviceId: String)
    fun getDeviceId(): String
    fun hasDeviceId(): Boolean
}

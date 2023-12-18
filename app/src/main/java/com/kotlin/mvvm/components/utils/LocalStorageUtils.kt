package com.kotlin.mvvm.components.utils

import android.content.Context
import android.content.SharedPreferences

class LocalStorageUtils(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "user_preferences",
        Context.MODE_PRIVATE
    )

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()
}
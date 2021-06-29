package com.target.targetcasestudy.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsHelper @Inject constructor(var mSharedPreferences: SharedPreferences){

    fun put(key: String?, value: String?) {
        mSharedPreferences!!.edit().putString(key, value).apply()
    }

    fun put(key: String?, value: Int) {
        mSharedPreferences!!.edit().putInt(key, value).apply()
    }

    operator fun get(key: String?, defaultValue: String?): String? {
        return mSharedPreferences!!.getString(key, defaultValue)
    }

    operator fun get(key: String?, defaultValue: Int): Int? {
        return mSharedPreferences!!.getInt(key, defaultValue)
    }
}
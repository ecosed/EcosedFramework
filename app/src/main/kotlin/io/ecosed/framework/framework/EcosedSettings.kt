package io.ecosed.framework.framework

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import io.ecosed.framework.framework.EnvironmentUtils.isWatch
import java.util.Locale


object EcosedSettings {

    private const val LANGUAGE = "language"
    private const val NIGHT_MODE = "night_mode"

    private lateinit var mSharedPreferences: SharedPreferences

    fun getPreferences(): SharedPreferences {
        return mSharedPreferences
    }

    fun initialize(context: Context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    @AppCompatDelegate.NightMode
    fun getNightMode(context: Context): Int {
        var defValue = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        if (isWatch(context)) {
            defValue = AppCompatDelegate.MODE_NIGHT_YES
        }
        return getPreferences().getInt(NIGHT_MODE, defValue)
    }

    fun getLocale(): Locale {
        val tag = getPreferences().getString(LANGUAGE, null)
        return if (TextUtils.isEmpty(tag) || "SYSTEM" == tag) {
            Locale.getDefault()
        } else {
            Locale.forLanguageTag(tag!!)
        }
    }
}
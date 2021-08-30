package com.tianhaoz95.lifestyletrackervoice_first.utilities

import android.app.Activity
import android.content.Context
import com.firebase.ui.auth.BuildConfig
import javax.inject.Inject

class LocalSettingsManager @Inject constructor(
    private val activity: Activity
) {
    companion object {
        private const val isDeveloperKey: String = "isDeveloper"
        private const val isDeveloperDefault: Boolean = false
        private const val shouldReportCrashKey: String = "shouldReportCrash"
        private const val shouldReportCrashDefault: Boolean = true
    }

    private fun getBooleanLocalSetting(
        key: String, default: Boolean
    ): Boolean {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        return if (sharedPref.contains(key)) {
            sharedPref.getBoolean(key, default)
        } else {
            default
        }
    }

    private fun setBooleanLocalSetting(
        key: String, update: Boolean
    ) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean(key, update)
            apply()
        }
    }

    private fun isDebugBuild(): Boolean {
        return BuildConfig.DEBUG
    }

    fun getIsDeveloper(): Boolean {
        return getBooleanLocalSetting(
            isDeveloperKey, isDeveloperDefault
        ) || isDebugBuild()
    }

    fun setIsDeveloper(update: Boolean) {
        setBooleanLocalSetting(isDeveloperKey, update)
    }

    fun getShouldReportCrash(): Boolean {
        return getBooleanLocalSetting(
            shouldReportCrashKey, shouldReportCrashDefault
        )
    }

    fun setShouldReportCrash(update: Boolean) {
        setBooleanLocalSetting(shouldReportCrashKey, update)
    }
}

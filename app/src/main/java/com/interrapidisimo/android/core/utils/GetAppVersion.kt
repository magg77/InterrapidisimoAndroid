package com.interrapidisimo.android.core.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.interrapidisimo.android.BuildConfig

object GetAppVersion {

    fun getAppVersion(context: Context): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                //"Version: ${packageInfo.versionName} (${packageInfo.longVersionCode})"
                "${packageInfo.longVersionCode}"
            } else {
                "${BuildConfig.VERSION_CODE}"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            "Unknown"
        }
    }

}
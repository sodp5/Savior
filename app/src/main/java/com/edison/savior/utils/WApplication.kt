package com.edison.savior.utils

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

class WApplication : Application() {
    companion object { var debugMode: Boolean = true }

    private fun isDebuggable(context: Context): Boolean {
        var debuggable = false

        val pm = context.packageManager
        try {
            val appInfo = pm.getApplicationInfo(context.packageName, 0)
            debuggable = (0 != ((appInfo.flags) and ApplicationInfo.FLAG_DEBUGGABLE))
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return debuggable
    }

    override fun onCreate() {
        super.onCreate()
        debugMode = isDebuggable(this)
    }


}
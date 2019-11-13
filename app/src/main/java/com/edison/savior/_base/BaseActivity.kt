package com.edison.savior._base

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.edison.savior.R

abstract class BaseActivity : AppCompatActivity() {
    companion object {
        const val STATUS_RED = R.color.mainStatusBar
        const val STATUS_WHITE = R.color.subStatusBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("log", "base~")
        window.statusBarColor = ContextCompat.getColor(this, setStatusBarColor())
    }

    @ColorRes
    abstract fun setStatusBarColor(): Int
}
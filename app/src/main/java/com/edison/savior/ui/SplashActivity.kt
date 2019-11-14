package com.edison.savior.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.edison.savior.R
import com.edison.savior._base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun setStatusBarColor() = STATUS_RED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(application, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}

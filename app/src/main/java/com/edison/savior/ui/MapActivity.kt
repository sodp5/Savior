package com.edison.savior.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edison.savior.R
import com.edison.savior._base.BaseActivity

class MapActivity : BaseActivity() {

    override fun setStatusBarColor() = STATUS_WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
    }

}

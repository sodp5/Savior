package com.edison.savior.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edison.savior.R

class LocationActivity : AppCompatActivity() {
    private val STATE_SAFE = "간단한 처치 필요"
    private val STATE_WARNING = "처치 필요"
    private val STATE_DANGER = "즉각적 응급처치 필요"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

    }
}

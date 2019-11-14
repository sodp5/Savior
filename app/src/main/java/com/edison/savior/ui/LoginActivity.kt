package com.edison.savior.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edison.savior.R
import com.edison.savior._base.BaseActivity
import com.edison.savior.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    val _id = MutableLiveData<String>()
    private val id: LiveData<String> get() = _id
    val _pw = MutableLiveData<String>()
    private val pw: LiveData<String> get() = _pw

    private val ADMIN_ID = "admin"
    private val ADMIN_PW = "1234"

    override fun setStatusBarColor() = STATUS_WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activity = this
        binding.lifecycleOwner = this
    }

    fun login() {
        if (id.value.equals(ADMIN_ID)) {
            if (pw.value.equals(ADMIN_PW)) {
                startActivity(Intent(applicationContext, PeopleLocationActivity::class.java))
                finish()
            }
            else {
                Toast.makeText(applicationContext, "incorrect PW!!", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toast.makeText(applicationContext, "incorrect ID!!", Toast.LENGTH_SHORT).show()
        }
    }
}

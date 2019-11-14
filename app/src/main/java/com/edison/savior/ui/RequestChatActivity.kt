package com.edison.savior.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edison.savior.R
import com.edison.savior.databinding.ActivityRequestChatBinding
import kotlinx.android.synthetic.main.activity_request_chat.*
import android.view.LayoutInflater
import android.widget.TextView
import com.edison.savior._base.BaseActivity

class RequestChatActivity : BaseActivity() {
    private lateinit var binding: ActivityRequestChatBinding

    private val adapter: ArrayAdapter<String> by lazy {
        object: ArrayAdapter<String>(this, R.layout.item_request_chat) {
            lateinit var view: View

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                view = if (convertView == null) {
                    val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    inflater.inflate(R.layout.item_request_chat, parent, false)
                } else {
                    convertView
                }

                view.findViewById<TextView>(R.id.txt_myMessage).text = getItem(position)
                return view
            }
        }
    }

    override fun setStatusBarColor() = STATUS_WHITE

    val _message = MutableLiveData<String>()
    private val message: LiveData<String> get() = _message

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_request_chat)
        binding.activity = this
        binding.lifecycleOwner = this

        lv_display_chat.adapter = adapter
    }

    fun sendMessage() {
        adapter.add(message.value)
        adapter.notifyDataSetChanged()
        _message.value = ""
        lv_display_chat.smoothScrollToPosition(adapter.count)
    }
}

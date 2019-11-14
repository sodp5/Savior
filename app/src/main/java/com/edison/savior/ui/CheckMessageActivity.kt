package com.edison.savior.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.edison.savior.R
import com.edison.savior._base.BaseActivity
import kotlinx.android.synthetic.main.activity_check_message.*

class CheckMessageActivity : BaseActivity() {
    private val adapter: ArrayAdapter<PersonInfo> by lazy {
        object: ArrayAdapter<PersonInfo>(this, R.layout.item_person_locate_exist_message) {
            lateinit var view: View

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                view = if (convertView == null) {
                    val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    inflater.inflate(R.layout.item_person_locate_exist_message, parent, false)
                } else {
                    convertView
                }

                val character = when(rotation++ % 4) {
                    0 -> R.drawable.character_one
                    1 -> R.drawable.character_two
                    2 -> R.drawable.character_three
                    3 -> R.drawable.character_four
                    else -> 0
                }
                view.findViewById<ImageView>(R.id.iv_character).setImageResource(character)
                view.findViewById<TextView>(R.id.txt_personName).text = getItem(position)!!.personName
                view.findViewById<TextView>(R.id.txt_personLocate).text = getItem(position)!!.personLocate
                view.findViewById<View>(R.id.view_stateColor).background = getDrawable(getItem(position)!!.stateColor)
                view.findViewById<TextView>(R.id.txt_stateInfo).text = getItem(position)!!.stateInfo

                return view
            }
        }
    }

    companion object {
        private const val STATE_SAFE = "간단한 처치 필요"
        private const val STATE_WARNING = "처치 필요"
        private const val STATE_DANGER = "즉각적 응급처치 필요"

        private var rotation = 2
    }

    override fun setStatusBarColor() = STATUS_WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_message)

        addPerson("김민지", "H동208호", STATE_DANGER)
        addPerson("고명진", "H동208호", STATE_WARNING)

        lv_existMessagePeopleList.adapter = adapter
        lv_existMessagePeopleList.setOnItemClickListener { _, _, _, _ ->
            startActivity(Intent(applicationContext, RequestChatActivity::class.java).apply {
                putExtra("isExistMessage", "come") })
        }
    }

    fun addPerson(personName: String, personLocate: String, stateInfo: String) {
        adapter.add(PersonInfo(personName, personLocate, stateInfo))
        adapter.notifyDataSetChanged()
    }

    private data class PersonInfo(val personName: String, val personLocate: String,
                                  val stateInfo: String) {
        val stateColor: Int = when (stateInfo) {
            STATE_SAFE -> R.color.stateSafe
            STATE_WARNING -> R.color.stateWarning
            STATE_DANGER -> R.color.stateDanger
            else -> R.color.white
        }
    }
}

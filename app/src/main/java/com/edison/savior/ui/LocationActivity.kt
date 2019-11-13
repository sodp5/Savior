package com.edison.savior.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.edison.savior.R
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity() {
    private val adapter: ArrayAdapter<PersonInfo> by lazy {
        object: ArrayAdapter<PersonInfo>(this, R.layout.item_person_locate) {
            lateinit var view: View

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                view = if (convertView == null) {
                    val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    inflater.inflate(R.layout.item_person_locate, parent, false)
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

        private var rotation = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        addPerson("안경문", "H동208호", STATE_SAFE)
        addPerson("김주성", "H동208호", STATE_SAFE)
        addPerson("고명진", "H동208호", STATE_WARNING)
        addPerson("김민지", "H동208호", STATE_DANGER)

        lv_peopleList.adapter = adapter
    }

    fun addPerson(personName: String, personLocate: String, stateInfo: String) {
        adapter.add(PersonInfo(personName, personLocate, stateInfo))
    }

    private data class PersonInfo(val personName: String, val personLocate: String,
                                  val stateInfo: String) {
        val stateColor: Int by lazy {
            when (stateInfo) {
                STATE_SAFE -> R.color.stateSafe
                STATE_WARNING -> R.color.stateWarning
                STATE_DANGER -> R.color.stateDanger
                else -> R.color.white
            }
        }

    }
}

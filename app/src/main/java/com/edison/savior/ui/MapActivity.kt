package com.edison.savior.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.edison.savior.R
import com.edison.savior._base.BaseActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.item_poi_list.*

class MapActivity : BaseActivity() {
    private val adapter: ArrayAdapter<PoiInfo> by lazy {
        object: ArrayAdapter<PoiInfo>(this, R.layout.item_poi_list) {
            lateinit var view: View

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                view = if (convertView == null) {
                    val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    inflater.inflate(R.layout.item_poi_list, parent, false)
                } else {
                    convertView
                }

                view.findViewById<TextView>(R.id.txt_poiName).text = getItem(position)!!.poiName
                view.findViewById<TextView>(R.id.txt_poiJibun).text = getItem(position)!!.poiJibun
                view.findViewById<TextView>(R.id.txt_poiLoadName).text = getItem(position)!!.poiLoadName
                val poiFun = view.findViewById<LinearLayout>(R.id.ll_collapsePoiItemFun)
                val searchStateWrapper = view.findViewById<LinearLayout>(R.id.ll_searchStateWrapper)
                val imageSearchState = view.findViewById<ImageView>(R.id.iv_searchState)
                val textSearchState = view.findViewById<TextView>(R.id.txt_searchState)

                var isSearch = false

                searchStateWrapper.setOnClickListener {
                    if (isSearch) {
                        imageSearchState.setImageResource(R.drawable.btn_start_search)
                        textSearchState.text = "탐색 시작"
                    }
                    else {
                        imageSearchState.setImageResource(R.drawable.btn_during_search)
                        textSearchState.text = "탐색 중"
                    }
                    isSearch = !isSearch
                }

                view.findViewById<ConstraintLayout>(R.id.cl_poiItemWrapper).setOnClickListener {
                    if (poiFun.visibility == View.GONE)
                        poiFun.visibility = View.VISIBLE
                    else
                        poiFun.visibility = View.GONE
                }

                view.findViewById<LinearLayout>(R.id.ll_stopSearchWrapper).setOnClickListener {
                    if (isSearch) {
                        imageSearchState.setImageResource(R.drawable.btn_start_search)
                        textSearchState.text = "탐색 시작"
                        isSearch = !isSearch
                    }
                }

                view.findViewById<LinearLayout>(R.id.ll_checkMessageWrapper).setOnClickListener {
                    startActivity(Intent(applicationContext, CheckMessageActivity::class.java))
                }

                view.findViewById<LinearLayout>(R.id.ll_checkLocationWrapper).setOnClickListener {
                    startActivity(Intent(applicationContext, PeopleLocationActivity::class.java))
                }


                return view
            }
        }
    }

    override fun setStatusBarColor() = STATUS_WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        addPoi("경기과학기술대학교 중소기업관", "정왕동 2122-1", "경기 시흥시 경기과기대로 270")
        addPoi("경기과학기술대학교 제1캠퍼스", "정왕동 2321-3", "경기 시흥시 경기과기대로 269")
        addPoi("경기과학기술대학교 다솜학사1관", "정왕동 2122-2", "경기 시흥시 경기과기대로 270")

        lv_poiList.adapter = adapter
    }

    fun addPoi(poiName: String, poiJibun: String, poiLoadName: String) {
        adapter.add(PoiInfo(poiName, poiJibun, poiLoadName))
        adapter.notifyDataSetChanged()
    }

    private data class PoiInfo(val poiName: String, val poiJibun: String,
                                  val poiLoadName: String)
}

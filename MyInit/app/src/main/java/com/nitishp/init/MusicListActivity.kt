package com.nitishp.init

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_musiclist.*

class MusicListActivity :AppCompatActivity() {
    private val dataArray: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musiclist)
        addDataArray()

        rv_data_list.layoutManager =LinearLayoutManager(this)
        rv_data_list.adapter =DataAdapter(dataArray,this)
    }
    private fun addDataArray(){

        dataArray.add("곡 제목1")
        dataArray.add("곡 제목2")
        dataArray.add("곡 제목3")
        dataArray.add("곡 제목4")
        dataArray.add("곡 제목5")
        dataArray.add("곡 제목6")
        dataArray.add("곡 제목7")
        dataArray.add("곡 제목8")
        dataArray.add("곡 제목9")
        dataArray.add("곡 제목10")


    }
    

}
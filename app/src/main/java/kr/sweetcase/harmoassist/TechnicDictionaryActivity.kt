package kr.sweetcase.harmoassist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.sweetcase.harmoassist.listMaterials.RecyclerDecoration
import kr.sweetcase.harmoassist.listMaterials.TechnicAdapter
import kr.sweetcase.harmoassist.listMaterials.TechnicalInfo


class TechnicDictionaryActivity : AppCompatActivity() {

    val techArray : ArrayList<TechnicalInfo> = ArrayList()

    lateinit var backBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technic_dictionary)

        /** 컴포넌트 초기화 **/
        backBtn = findViewById(R.id.back_btn_from_tech_list)

        // 데이터 삽입
        // TODO 얘는 프로토타입 용이고 나중에 데이터를 따로 추가할 예정
        for(i in 0..20) {
            techArray.add(
                TechnicalInfo(
                    "도미넌트 베리에이션 I",
                    "곡의 종지를 효과적으로 만들 수 있는 도미넌트 베리에이션 중 하나이며 도미넌트 모션을 강조할 때 사용한다.",
                    "tech_test.png")
            )
        }

        /** 리사이클러뷰 세팅 **/
        val recyclerView = findViewById<RecyclerView>(R.id.technic_list_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = TechnicAdapter(techArray, this)
        recyclerView.adapter = adapter

        /** 컴포넌트 리스너
         * 그냥 창닫기 **/
        backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}

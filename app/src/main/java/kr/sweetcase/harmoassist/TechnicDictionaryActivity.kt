package kr.sweetcase.harmoassist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.sweetcase.harmoassist.listMaterials.TechnicalInfo

class TechnicDictionaryActivity : AppCompatActivity() {

    val techArray : ArrayList<TechnicalInfo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technic_dictionary)

        // 데이터 삽입
        for(i in 0..20) {
            techArray.add(
                TechnicalInfo(
                    "도미넌트 베리에이션 I",
                    "곡의 종지를 효과적으로 만들 수 있는 도미넌트 베리에이션 중 하나이며 도미넌트 모션을 강조할 때 사용한다.",
                    "tech_test.png")
            )
        }
    }
}

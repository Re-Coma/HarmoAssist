package kr.sweetcase.harmoassist

import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.opengl.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kr.sweetcase.harmoassist.listMaterials.TechnicalInfo
import java.io.IOException

class TechnicSummaryActivity : AppCompatActivity() {

    lateinit var techInfo : TechnicalInfo

    lateinit var title : TextView
    lateinit var imgView : ImageView
    lateinit var summary : TextView
    lateinit var spinner: Spinner

    lateinit var activity : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technic_summary)

        title = findViewById(R.id.tech_summary_title)
        imgView = findViewById(R.id.tech_summary_img)
        summary = findViewById(R.id.tech_summary_text)
        spinner = findViewById(R.id.tech_test_spinner)
        activity = this
        // 데이터 전달받기
        techInfo = intent.getSerializableExtra("tech_info") as TechnicalInfo
        // TODO 재생용 미디데이터도 포함해야됨

        title.text = techInfo.techName

        // 이미지세팅
        try {

            val inputStream = resources.assets.open(techInfo.imgAssetName)
            imgView.setImageDrawable(Drawable.createFromStream(inputStream, null))



            inputStream.close()
        } catch(a : IOException) { // 이미지가 없을 경우
            /** TODO 서버로부터 직접 받는 코드 작성 **/
        }
        // 설명 세팅
        summary.text = techInfo.comment

        // 스피너 세팅
        val chordItems = resources.getStringArray(R.array.tech_chord_arr)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, chordItems)
        spinner.adapter = adapter

        // 스피너 리스너
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                // 선택된 코드 관련 데이터 획득
                val strChord = spinner.getItemAtPosition(position).toString()

                /** TODO 여기서 코드에 맞게 재생용 미디데이터 조정 **/
            }
        }
    }
}

package kr.sweetcase.harmoassist

import android.graphics.Point
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.widget.Spinner
import android.widget.SpinnerAdapter
import kotlinx.android.synthetic.main.activity_combo.*
import kr.sweetcase.harmoassist.dialogs.SelectedAIRandom

class ComboActivity :AppCompatActivity(){
    private var activity = this
    private var windowSize = Point()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combo)
        //Spinner 구현(콤보박스)
        // 박자 콤보박스 구현
        val beatSpinner :Spinner = findViewById<Spinner>(R.id.spinner_beat) //박자 스피너 연결
        val beatAdapter = ArrayAdapter.createFromResource(this,
            R.array.beat,android.R.layout.simple_spinner_dropdown_item) //박자 스피너와 데이터 연결할 어댑터 정의
        //android.R.layout.simple_spinner_dropdown_item 사용 --> 아래로 스크롤 내려주며 아이템 보여줌
        beatSpinner.adapter = beatAdapter // 박자 스피너의 어댑터 설정

        val chordSpinner = findViewById<Spinner>(R.id.spinner_chord) //코드 스피너 연결
        val chordAdapter = ArrayAdapter.createFromResource(this,
            R.array.chord,android.R.layout.simple_spinner_dropdown_item) //코드 스피너와 데이터 연결할 어댑터 정의
        chordSpinner.adapter = chordAdapter //코드 스피너의 어댑터 설정

        //버튼 눌렀을 경우의 구현부분

        AI_RANDOM_Btn.setOnClickListener{
            /*AI 랜덤 버튼 눌렀을 경우*/
            val dialog = SelectedAIRandom(activity, windowSize)
            dialog.show()
        }
        BackBtn_from_Create.setOnClickListener{
            /*취소 버튼 눌렀을 경우 */
            finish()
        }
    }

}
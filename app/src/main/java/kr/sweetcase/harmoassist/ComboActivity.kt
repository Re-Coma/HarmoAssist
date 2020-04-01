package kr.sweetcase.harmoassist

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_combo.*

/*
콤보 박스 구현 및 dialog 구현 부분
 */
class ComboActivity :AppCompatActivity(){

    private var windowSize = Point()
    private var aiSelected = false //ai 옵션 설정 여부
    var selectedAIOption = "" // ai옵션 아이디

    /*** TODO Android Midi Control Library의 변수가 들어가야 함 ***/

    // 스피너 리스너들

    // 1.AI 옵션 리스너
    class AIOptionSelectedListener(comboActivity: ComboActivity) : OnItemSelectedListener {
        var activity : ComboActivity = comboActivity
        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            this.activity.selectedAIOption = parent?.getItemAtPosition(pos).toString()
            // test
            Toast.makeText(parent?.context, this.activity.selectedAIOption, Toast.LENGTH_SHORT).show()
        }
    }

    // 박자 옵션 리스너
    class TimeSignatureSelectedListener(comboActivity: ComboActivity) : OnItemSelectedListener {
        var activity = comboActivity

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            // TODO 이때 선택된 박자가 삽입되어야 하는데 이거는 백엔드 도입 때 구현
        }
    }

    // 코드 옵션 리스너
    class ChordSelectedListener(comboActivity: ComboActivity) : OnItemSelectedListener {
        var activity = comboActivity

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            // TODO 이때 선택된 박자가 삽입되어야 하는데 이거는 백엔드 도입 때 구현
        }

    }


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
        beatSpinner.onItemSelectedListener = TimeSignatureSelectedListener(this)


        val chordSpinner = findViewById<Spinner>(R.id.spinner_chord) //코드 스피너 연결
        val chordAdapter = ArrayAdapter.createFromResource(this,
            R.array.chord,android.R.layout.simple_spinner_dropdown_item) //코드 스피너와 데이터 연결할 어댑터 정의
        chordSpinner.adapter = chordAdapter //코드 스피너의 어댑터 설정
        chordSpinner.onItemSelectedListener = ChordSelectedListener(this)

        // AI 옵션 설정
        val aiOptionSpinner = findViewById<Spinner>(R.id.ai_option_spinner)
        val aiOptionAdapter = ArrayAdapter.createFromResource(this,
            R.array.ai_option_arr, android.R.layout.simple_spinner_dropdown_item)
        aiOptionSpinner.adapter = aiOptionAdapter
        aiOptionSpinner.onItemSelectedListener = AIOptionSelectedListener(this)

        //버튼 눌렀을 경우의 구현부분
        CreateBtn.setOnClickListener{
            /*생성 버튼 눌렀을 경우 구현 부분 */

            var music_name = edit_name.text.toString()
            if(edit_name.text.toString().isEmpty()){
                Toast.makeText(this,"이름이 공백입니다.",Toast.LENGTH_SHORT).show()
            }
            var music_speed = edit_speed.text.toString()
            if(edit_speed.text.toString().isEmpty()){
                Toast.makeText(this,"속도가 공백입니다.",Toast.LENGTH_SHORT).show()
            }
            var chord = chordSpinner.selectedItem.toString()
            if(chord== "") {
                Toast.makeText(this,"코드를 선택하십시오",Toast.LENGTH_SHORT).show()
            }
            var beat = beatSpinner.selectedItem.toString()
            if(beat== ""){
                Toast.makeText(this,"박자를 선택하십시오",Toast.LENGTH_SHORT).show()
            }
            var music_comment = edit_comment.text.toString()
            if(edit_comment.text.toString().isEmpty()){
                Toast.makeText(this,"코멘트가 공백입니다.",Toast.LENGTH_SHORT).show()
            }
            /*각 Edit Text와 스피너에서 값 읽어오는 부분*/
            /*클래스 형식으로 데이터 묶어서 전달(압축형식)*/
            var myMusic = Music(music_name,music_speed,chord,beat,music_comment)
            val intent = Intent(this,AiRandomactivity::class.java)

            // TODO 얘는 테스트 용으로 악보가 추가될 시 악보 액티비티로 이동
            // AI작동 여부 세팅
            if(aiSelected) {
                // TODO AI 작동
            } else {
                // TODO AI 미작동
            }

            intent.putExtra("CreateKey",myMusic)
            startActivity(intent)
            /*intent. put Extra 로 Music 클래스의 변수 myMusic을 저장*/
        }

        AI_RANDOM_Btn.setOnClickListener {
            if(aiSelected) {
                aiSelected = false
                aiOptionSpinner.visibility = View.VISIBLE
                it.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            }
            else {
                aiSelected = true
                aiOptionSpinner.visibility = View.GONE
                it.setBackgroundColor(ContextCompat.getColor(this, R.color.real_gray))
            }
        }
    }
}
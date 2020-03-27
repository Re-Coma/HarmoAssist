package kr.sweetcase.harmoassist

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_combo.*
import kr.sweetcase.harmoassist.dialogs.SelectedAIRandom
/*
콤보 박스 구현 및 dialog 구현 부분
 */
class ComboActivity :AppCompatActivity(){
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
            intent.putExtra("CreateKey",myMusic)
            startActivity(intent)
            /*intent. put Extra 로 Music 클래스의 변수 myMusic을 저장*/
        }
        AI_RANDOM_Btn.setOnClickListener{
            /*AI 랜덤 버튼 눌렀을 경우*/
            val dialog = SelectedAIRandom(this@ComboActivity)
            // 팝업창 표현
            dialog.show()
            /*다이얼로그에서 버튼 눌렀을 경우 intent로 값 전달
            전달하는 값은 수정 필요 (임시로 String 값을 전달하도록 만들어놓음)
            */
            dialog.spBtn.setOnClickListener{
                var intent = Intent(this, AiRandomactivity::class.java)
                intent.putExtra("musicKey","Chopin")
                startActivity(intent)
            }
            dialog.btnBtn.setOnClickListener{
                var intent = Intent(this, AiRandomactivity::class.java)
                intent.putExtra("musicKey","Beethoven")
                startActivity(intent)
        }
            dialog.scalBtn.setOnClickListener {
                var intent = Intent(this, AiRandomactivity::class.java)
                intent.putExtra("musicKey", "Scarlatti")
                startActivity(intent)
            }
            dialog.balBtn.setOnClickListener{
                var intent = Intent(this, AiRandomactivity::class.java)
                intent.putExtra("musicKey", "ballad")
                startActivity(intent)
            }
            dialog.cancleBtn.setOnClickListener{
                dialog.dismiss()
            }
        }
        BackBtn_from_Create.setOnClickListener{
            /*취소 버튼 눌렀을 경우 */
            finish()
        }
    }

}
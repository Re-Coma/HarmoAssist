package kr.sweetcase.harmoassist

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ai_random_per.*
/*
임시로 AIrandom 화면을 만들어 놓음 --> AI 작곡시에 Intent로 전달해주는것 미리 구현

 */

class AiRandomactivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.ai_random_per)
        /*Create 버튼이 눌렸을 경우의 코드*/

        if(intent.hasExtra("CreateKey")){
            var music = intent.getParcelableExtra<Music>("CreateKey")
            /*Music 변수를 초기화할때 자료형이 정해지지 않아
            * getparcelableExtra 뒤에 <>가 생기고 ,이곳에 자료형을 입력한다.*/


            getIntent.text = "데이터 받음" //여기까진 출력됨 아래가 출력되지않는 문제
            Toast.makeText(this,"${music?.name} " +
                    "곡의 빠르기는 ${music?.speed} " +
                    "박자는 ${music?.beat} " +
                    "코드는 ${music?.chord} " +
                    "코멘트는 ${music?.comment}입니다.",Toast.LENGTH_SHORT)
        }


        /*ai 랜덤이 선택되었을 경우의 코드*/
            if (intent.hasExtra("musicKey")) {
                getIntent.text = intent.getStringExtra("musicKey")
                /* "musicKey"라는 이름의 key에 저장된 값이 있다면
                   textView의 내용을 "musicKey" key에서 꺼내온 값으로 바꾼다 */

            } else {
                Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
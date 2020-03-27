package kr.sweetcase.harmoassist

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ai_random_per.*

class AiRandomactivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.ai_random_per)
            if (intent.hasExtra("nameKey")) {
                getIntent.text = intent.getStringExtra("nameKey")
                /* "nameKey"라는 이름의 key에 저장된 값이 있다면
                   textView의 내용을 "nameKey" key에서 꺼내온 값으로 바꾼다 */

            } else {
                Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
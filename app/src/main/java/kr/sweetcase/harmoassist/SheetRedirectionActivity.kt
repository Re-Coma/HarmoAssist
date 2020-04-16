package kr.sweetcase.harmoassist

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.sweetcase.harmoassist.listMaterials.Music

// TODO 여기에서 악보 인터페이스로 들어가는 코드 작성하면 됨.

/**
 * TODO 악보를 생성하는 경우는 다음과 같다.
 * 1. 새 악보 생성: 말 그대로 새 악보 생성
 * 2. AI를 이용한 새 악보 생성 : 새 악보를 생성하되 비어있는 악보가 아닌 작곡된 악보
 * 3. 기존에 있는 악보 생성: 작곡된 악보를 생성
 *
 * 인텐트 파라미터는 MakeSheetType 참고
 */
class SheetRedirectionActivity : AppCompatActivity() {

    private var backKeyClickTime : Long = 0
    private lateinit var musicInfoData : Music

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheet_redirection)

        // 타입 확인
        val type = intent.extras?.getString("type")

        if(type != null) {
            when(type) {
                // TODO 데이터 준비

                MakeSheetType.CURRENT.key -> {

                    // intent 데이터 추출
                    musicInfoData = intent.extras?.getSerializable(MakeSheetType.CURRENT.intentKeys[0]) as Music

                    // TODO 현재 있는 악보를 불러오는 경우
                    // TODO DB에 접속해서 모든 미디데이터를 불러오기
                    Toast.makeText(this, "Current Sheet Loading", Toast.LENGTH_LONG).show()
                }
                MakeSheetType.NEW.key -> {

                    // intent 데이터 추출
                    musicInfoData = intent.extras?.getSerializable(MakeSheetType.NEW.intentKeys[0]) as Music

                    // TODO 새롭게 만드는 경우이므로
                    // TODO 생성된 악보 정보 데이터를 DB에 저장만 하면 됨
                    Toast.makeText(this, "New Sheet Loading", Toast.LENGTH_LONG).show()
                }
                MakeSheetType.NEW_AI.key -> {

                    // intent 데이터 추출
                    musicInfoData = intent.extras?.getSerializable(MakeSheetType.NEW_AI.intentKeys[0]) as Music
                    val aiOptionStr = intent.extras?.getString(MakeSheetType.NEW_AI.intentKeys[1])
                    val noteSize = intent.extras?.getInt(MakeSheetType.NEW_AI.intentKeys[2])

                    // TODO 서버에 접속해서 딥러닝을 수행한 다음
                    // TODO 수행된 딥러닝 데이터 배열을
                    // TODO Midi Library에 저장해야 한다.

                    Toast.makeText(this, "$noteSize", Toast.LENGTH_LONG).show()
                }
            }

        }
        // TODO 데이터가 준비가 다 되었다면 여기서 악보 인터페이스 액티비티로 전환
    }

    // TODO 뒤로 가기 버튼을 눌렀을 경우
    override fun onBackPressed() {
        if(System.currentTimeMillis() > backKeyClickTime + 2000) {
            backKeyClickTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로 가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            // TODO 상황 정리
            // 종료
            super.onBackPressed()
        }
    }
}

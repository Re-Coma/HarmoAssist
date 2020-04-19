package kr.sweetcase.harmoassist

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import io.lettuce.core.RedisConnectionException
import kotlinx.android.synthetic.main.activity_sheet_redirection.*
import kotlinx.coroutines.*
import kr.sweetcase.harmoassist.listMaterials.Music
import kr.sweetcase.harmoassist.modules.AIConnectionModule.AIClientTask
import kotlin.coroutines.CoroutineContext

// TODO 여기에서 악보 인터페이스로 들어가는 코드 작성하면 됨.

/**
 * TODO 악보를 생성하는 경우는 다음과 같다.
 * 1. 새 악보 생성: 말 그대로 새 악보 생성
 * 2. AI를 이용한 새 악보 생성 : 새 악보를 생성하되 비어있는 악보가 아닌 작곡된 악보
 * 3. 기존에 있는 악보 생성: 작곡된 악보를 생성
 *
 * 인텐트 파라미터는 MakeSheetType 참고
 */
class SheetRedirectionActivity : AppCompatActivity(), CoroutineScope {

    private var backKeyClickTime : Long = 0
    private lateinit var musicInfoData : Music
    private var type : String? = null
    private var context : Context = this

    // task
    private lateinit var mJob : Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheet_redirection)

        // 타입 확인
        type = intent.extras?.getString("type")

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
                    mJob = Job()

                    // dialogBuilder
                    val dialogBuilder = AlertDialog.Builder(this@SheetRedirectionActivity)


                    // 예외 핸들러
                    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->

                        // TODO 나중에 타입에 따라 다르게 설정해야 할 필요가 있음
                        val alert = dialogBuilder
                            .setTitle("ERROR")
                            .setMessage(throwable.message.toString())
                            .setPositiveButton("확인", DialogInterface.OnClickListener {
                                dialog, _ ->
                                dialog.dismiss()
                                finish()
                            })
                            .create()
                        alert.show()
                    }

                    launch(exceptionHandler) {
                        val deferred = async(Dispatchers.Default) {
                            musicInfoData = intent.extras?.getSerializable(MakeSheetType.NEW_AI.intentKeys[0]) as Music
                            val aiOptionStr = intent.extras?.getString(MakeSheetType.NEW_AI.intentKeys[1])
                            val noteSize = intent.extras?.getInt(MakeSheetType.NEW_AI.intentKeys[2])

                            // TODO DB에서 서버와 관련된 정보를 불러온다. (이거는 나중에 구현)

                            // text 변경
                            loading_test.text = "서버 연결 중.."

                            // 서버 접속
                            val clientConnection = AIClientTask.Builder()
                                .setContext(context)
                                .setHost("sweetcase.tk")
                                .setPort(7890)
                                .setPswd("4680")
                                .setSerial("avbk2#$@skd#%")
                                .build()
                            clientConnection.connect()
                        }
                        deferred.await()
                    }
                    // TODO 서버에 접속해서 딥러닝을 수행한 다음
                    // TODO 수행된 딥러닝 데이터 배열을
                    // TODO Midi Library에 저장해야 한다.
                }
            }
        }
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

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }

}

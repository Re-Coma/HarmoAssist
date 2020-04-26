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
import kr.sweetcase.harmoassist.modules.AIConnectionModule.labels.RequestData
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
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

    private var clientConnection: AIClientTask? = null

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

                    // TODO 악보 인터페이스 실행
                }
                MakeSheetType.NEW.key -> {

                    // intent 데이터 추출
                    musicInfoData = intent.extras?.getSerializable(MakeSheetType.NEW.intentKeys[0]) as Music

                    // TODO 새롭게 만드는 경우이므로
                    // TODO 생성된 악보 정보 데이터를 DB에 저장만 하면 됨
                    Toast.makeText(this, "New Sheet Loading", Toast.LENGTH_LONG).show()

                    // TODO 악보 인터페이스 실행
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

                            // text 변경
                            loading_test.text = "서버 연결 중.."

                            // 서버 접속
                            clientConnection = AIClientTask.Builder()
                                .setContext(context)
                                .setHost("sweetcase.tk")
                                .setPort(7890)
                                .setPswd("4680")
                                .setSerial("avbk2#$@skd#%")
                                .build()
                            clientConnection?.connect()

                            // 서버 접속 확인
                            if(clientConnection!!.connected) {
                                loading_test.text = "요청 데이터 송신중"

                                // TODO 서버에 딥러닝 요청 데이터 전송 및 수신
                                val requestJson = RequestData(
                                    clientConnection?.clientInfo!!.myIP,
                                    aiOptionStr,
                                    musicInfoData.timeSignature,
                                    noteSize
                                )
                                clientConnection?.sendRequestData(requestJson)

                                // TODO 데이터 받기
                                val rawData = clientConnection?.receiveResultRawData()

                                // TODO 데이터 나열(알고리즘 구현)
                                loading_test.text = "데이터 나열 중..."

                                // TODO Midi파일이 제데로 저장이 되었는지 테스트할 필요가있다.
                                try {
                                    val os = openFileOutput("result.mid", Context.MODE_PRIVATE)
                                    os.write(rawData?.toByteArray())
                                } catch(e : Exception) {
                                    throw Exception("파일을 저장하는 데 실패했습니다.")
                                }

                                val iis = openFileInput("result.mid")

                                // TODO 미디파일이 제대로 입력되었는지 확인할 필요가 있음


                                // TODO 반주 생성

                                // TODO 서버 연결 끊기
                                loading_test.text = "서버와의 연결을 끊는 중"
                                clientConnection?.disconnect()

                                // TODO DB에 저장
                                loading_test.text = "데이터베이스에 저장중"

                                // TODO 악보 인터페이스 실행
                            } else {
                                throw Exception("서버와의 연결에 실패했습니다.")
                            }
                        }
                        deferred.await()
                    }
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
            mJob.cancel()
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
        if(this.clientConnection != null) {
            if(this.clientConnection!!.connected) {
                this.clientConnection!!.conn.shutdown()
            }
        }
    }

}

package kr.sweetcase.harmoassist.modules.AIConnectionModule

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import io.lettuce.core.RedisClient
import io.lettuce.core.RedisConnectionException
import io.lettuce.core.RedisURI
import kr.sweetcase.harmoassist.modules.AIConnectionModule.labels.ConnectionData
import kr.sweetcase.harmoassist.modules.AIConnectionModule.labels.RedisClientData
import kr.sweetcase.harmoassist.modules.AIConnectionModule.labels.RedisServerData
import java.net.ConnectException

// AI 수행 클라이언트 클래스
class AIClientTask {
    var conn : RedisClient //연결이 없는 상태일 때는 null유지
    var serverInfo : RedisServerData
    var clientInfo: RedisClientData
    var connected : Boolean = false
    var key : Int? = null

    // Static Value
    companion object {
        const val SERVER_CHANNEL = "DeepServerPipe"
    }

    private constructor(connections : RedisClient, serverInfo : RedisServerData, clientInfo : RedisClientData) {
        this.serverInfo = serverInfo
        this.clientInfo = clientInfo
        this.conn = connections
    }

    // AIClient Builder
    class Builder {
        private lateinit var context: Context
        private var host : String = ""
        private var port : Int = 0
        private var pswd : String = ""
        private var serial : String = ""


        // TODO 유효성 판정
        fun setContext(context : Context) : Builder { this.context = context; return this }
        fun setHost(host : String) : Builder { this.host = host; return this  }
        fun setPort(port : Int) : Builder { this.port = port; return this  }
        fun setPswd(pswd : String) : Builder { this.pswd = pswd; return this  }
        fun setSerial(serial : String) : Builder { this.serial = serial; return this  }

        fun build() : AIClientTask {
            try {
                // TODO 데이터 유효성 판정
                val clientInfo = RedisClientData()
                val serverInfo = RedisServerData(this.host, this.port, this.pswd, this.serial)

                // Redis 생성
                val uri : RedisURI = RedisURI.Builder.redis(this.host)
                    .withPassword(this.pswd)
                    .withPort(this.port)
                    .withDatabase(0)
                    .build()
                val conn = RedisClient.create(uri)
                return AIClientTask(conn, serverInfo, clientInfo)
            } catch(e : Exception) {
                throw e
            }
        }
    }

    // 연결 시도
    fun connect() {
        // 이미 연결되어있는 지 확인
        if(this.connected) {
            throw ConnectException("aleady connected")
        }
        try {
            // 연결
            this.conn.connect()
            val connection = this.conn.connectPubSub().sync()

            // 연결 요청 Json 송신
            val requestConnectJson
                    = ConnectionData(this.clientInfo.myIP, this.serverInfo.serial).makeToJson()

            // Redis Server가 IPMap을 만들지 않았을 때 0L를 호출한다.
            if(connection.publish(AIClientTask.SERVER_CHANNEL, requestConnectJson) == 0L) {
                throw RedisConnectionException("서버에 응답이 없습니다.")
            }

            var connectLimitCounter = 0
            val connectLimit = 10

            // 서버로부터 키를 받음

            while(connectLimitCounter < connectLimit) {
                var rawKey = connection.hget(SERVER_CHANNEL, this.clientInfo.myIP)

                if(rawKey != null) {
                    try {
                        this.key = rawKey as Int
                    } catch (e : TypeCastException) {
                        // 서버로부터 잘못된 값이 생성
                        // TODO 서버 문제 관련 문제를 관리자에게 송신할 프로세스를 생성
                        throw e
                    }
                    this.connected = true
                    break
                }
                else {
                    // 다시 받을 때 까지 기다림
                    Thread.sleep(1000L)
                    connectLimitCounter++
                }
            }
            // 서버 연결에 실패
            if(!this.connected) {
                throw RedisConnectionException("서버가 응답이 없습니다.")
            }


            this.connected = true
        } catch(e : Exception) {
            throw e
        }
    }

    fun disconnect() {
        if(!this.connected) {
            throw ConnectException("aleady disconnected")
        }
        this.conn.shutdown()
        this.connected = false
    }

}
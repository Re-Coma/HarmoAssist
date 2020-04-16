package kr.sweetcase.harmoassist.modules.AIConnectionModule

import io.lettuce.core.RedisClient
import kr.sweetcase.harmoassist.modules.AIConnectionModule.labels.RedisServerData

class AIClientTask {
    lateinit var conn : RedisClient
    lateinit var serverInfo : RedisServerData
}
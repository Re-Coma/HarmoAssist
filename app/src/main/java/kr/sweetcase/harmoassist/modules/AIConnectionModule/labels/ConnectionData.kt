package kr.sweetcase.harmoassist.modules.AIConnectionModule.labels

import kr.sweetcase.harmoassist.modules.AIConnectionModule.DataModel

class ConnectionData(val myIP : String, val Serial : String) : DataModel() {
    val connectSignal = "CONNECT"

    override fun makeToJson() : String {
        var strJson = ""
        return strJson
    }
}
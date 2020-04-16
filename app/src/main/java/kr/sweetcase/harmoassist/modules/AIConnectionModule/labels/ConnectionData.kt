package kr.sweetcase.harmoassist.modules.AIConnectionModule.labels

import kr.sweetcase.harmoassist.modules.AIConnectionModule.DataModel
import org.json.JSONObject

class ConnectionData(val myIP : String, val serial : String) : DataModel() {
    val connectSignal = "CONNECT"

    override fun makeToJson() : String {
        val jsonObj = JSONObject()
        jsonObj.put("type", 0)
        jsonObj.put("myIP", myIP)
        jsonObj.put("serial", serial)
        return jsonObj.toString()
    }
}
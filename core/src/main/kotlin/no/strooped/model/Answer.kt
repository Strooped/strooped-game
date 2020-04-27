package no.strooped.model

import org.json.JSONObject

data class Answer(val answer: String?, val timestamp: Long) {
    fun toJson(): JSONObject {
        val json = JSONObject()
        json.put("answer", answer)
        json.put("timestamp", timestamp)
        return json
    }
}

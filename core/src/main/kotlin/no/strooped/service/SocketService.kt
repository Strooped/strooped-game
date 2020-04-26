package no.strooped.service

import com.badlogic.gdx.Gdx
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject

class SocketService {
    private var socketInstance: Socket? = null
    var listener: ((Socket) -> Unit)? = null
    var listeners: HashMap<String, ((JSONObject) -> Unit)> = HashMap()
    fun onConnect(callback: (Socket) -> Unit) {
        listener = callback
    }
    fun connect(joinPin: String, username: String) {
        val socket = IO.socket("https://strooped-api.lokalvert.tech", createIOOptions(joinPin, username))
        socketInstance = socket
        socket.on(Socket.EVENT_CONNECT) {
            Gdx.app.postRunnable {
                listener?.invoke(socket)
            }
        }
        socket.on(Socket.EVENT_ERROR) {
            println("Im failing")
        }
        listeners.forEach { (type, callback) ->
            socket.on(type) {
                Gdx.app.postRunnable {
                    callback(it[0] as JSONObject)
                }
            }
        }
        socket.connect()
    }
    fun onEvent(type: String, callback: (JSONObject) -> Unit) {
        listeners[type] = callback
    }
    fun sendAnswer(json: JSONObject) {
        socketInstance?.emit("task:answer", json)
    }
    private fun createIOOptions(joinPin: String, username: String): IO.Options {
        val opts = IO.Options()
        opts.query = "token=$joinPin&username=$username"
        return opts
    }
}

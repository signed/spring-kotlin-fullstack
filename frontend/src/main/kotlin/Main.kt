import common.User
import org.w3c.dom.EventSource
import org.w3c.dom.MessageEvent
import org.w3c.dom.events.Event

import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    window.onload = {
        val eventSource = EventSource("/api/users")
        val onMessage: (Event) -> dynamic = {
            // TODO Use it.data when KT-20743 will be fixed
            val user = JSON.parse<User>((it as MessageEvent).data as String)
            var li = document.createElement("li").apply {
                innerHTML = "User: ${user.firstName} ${user.lastName}"
            }
            document.getElementById("users")!!.appendChild(li)
        }
        // TODO Use eventSource.onmessage when KT-20741 will be fixed
        eventSource.addEventListener("message", onMessage)
    }
}


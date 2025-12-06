package org.example.firstcmpapp

actual fun logMessage(tag: String, msg: String) {
    webLog(tag, msg)
}


expect fun webLog(tag: String, msg: String)

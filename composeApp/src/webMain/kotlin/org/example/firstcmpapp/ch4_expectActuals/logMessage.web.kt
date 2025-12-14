package org.example.firstcmpapp.ch4_expectActuals

actual fun logMessage(tag: String, msg: String) {
    webLog(tag, msg)
}


expect fun webLog(tag: String, msg: String)

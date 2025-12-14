package org.example.firstcmpapp.ch4_expectActuals

actual fun webLog(tag: String, msg: String) {
    println("[$tag]: $msg")
}

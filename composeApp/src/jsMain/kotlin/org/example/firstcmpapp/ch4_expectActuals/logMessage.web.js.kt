package org.example.firstcmpapp.ch4_expectActuals

actual fun webLog(tag: String, msg: String) {
    console.log("[$tag]: $msg")
}
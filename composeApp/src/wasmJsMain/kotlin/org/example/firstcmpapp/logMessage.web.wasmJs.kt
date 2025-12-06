package org.example.firstcmpapp

actual fun webLog(tag: String, msg: String) {
    println("[$tag]: $msg")
}

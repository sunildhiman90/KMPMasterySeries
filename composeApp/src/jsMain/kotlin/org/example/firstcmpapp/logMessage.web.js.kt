package org.example.firstcmpapp

actual fun webLog(tag: String, msg: String) {
    console.log("[$tag]: $msg")
}
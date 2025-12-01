package org.example.firstcmpapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
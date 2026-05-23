package org.example.firstcmpapp.ch5_networkingHttpClientRequestResponses

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientProvider {
    private const val BASE_URL = "https://dummyjson.com"

    val client = HttpClient {

        expectSuccess = false
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }
            )
        }

        install(Logging) {
            level = LogLevel.ALL
           // logger = Logger.SIMPLE
            logger =  object : Logger {
                override fun log(message: String) {
                    println("ApiRequest: $message")
                }
            }
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 30000
        }


        defaultRequest {
            url(BASE_URL)
            header("Accept", "application/json")
        }
    }

}
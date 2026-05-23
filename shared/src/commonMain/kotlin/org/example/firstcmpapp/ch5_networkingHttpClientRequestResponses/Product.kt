package org.example.firstcmpapp.ch5_networkingHttpClientRequestResponses

import kotlinx.serialization.Serializable

@Serializable
class Product(
    val id: Int,
    val title: String,
    val price: Double
)
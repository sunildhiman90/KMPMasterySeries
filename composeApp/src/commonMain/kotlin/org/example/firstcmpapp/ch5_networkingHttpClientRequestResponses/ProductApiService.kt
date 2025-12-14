package org.example.firstcmpapp.ch5_networkingHttpClientRequestResponses

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ProductApiService(
    private val client: HttpClient = HttpClientProvider.client
) {

    suspend fun getProducts(): ApiResult<List<Product>> = safeApiCall {
        client.get("/products").body()
    }

    suspend fun getProduct(id: Int): ApiResult<Product> = safeApiCall {
        client.get("/products/$id").body()
    }

    suspend fun createProduct(product: Product): ApiResult<Product> = safeApiCall {
        client.post("/products/add") {
            setBody(product)
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun getProductsWithAuthToken(token: String): ApiResult<List<Product>> = safeApiCall {
        client.get("/auth/products") {
            header("Authorization", "Bearer $token")
        }.body()
    }
}
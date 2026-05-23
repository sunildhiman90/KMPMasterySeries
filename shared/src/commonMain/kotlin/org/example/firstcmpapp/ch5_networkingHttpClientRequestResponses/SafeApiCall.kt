package org.example.firstcmpapp.ch5_networkingHttpClientRequestResponses

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException

suspend fun <T> safeApiCall(block: suspend () -> T): ApiResult<T> {
    return try {
        ApiResult.Success(block())
    } catch (e: RedirectResponseException) {
        ApiResult.Error("Redirect error: ${e.message}", e.response.status.value)
    }  catch (e: ClientRequestException) {
        ApiResult.Error("Client error: ${e.message}", e.response.status.value)
    } catch (e: ServerResponseException) {
        ApiResult.Error("Server error: ${e.message}", e.response.status.value)
    } catch (e: HttpRequestTimeoutException) {
        ApiResult.Error("Timeout error: ${e.message}")
    } catch (e: IOException) {
        ApiResult.Error("Network error: ${e.message}")
    } catch (e: Exception) {
        ApiResult.Error("Unknown error: ${e.message}")
    }
}



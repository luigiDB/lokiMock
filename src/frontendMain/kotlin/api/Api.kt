package api

import MockConfigurator
import SupportedHttpMethods
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

suspend fun addMock(mock: MockConfigurator) {
    jsonClient.post(MockConfigurator.path) {
        contentType(ContentType.Application.Json)
        setBody(mock)
    }
}

suspend fun restartMockServer() {
    jsonClient.delete(MockConfigurator.path) { }
}

suspend fun callUnderTestEndpoint(method: String, endpoint: String, request: String): HttpStatusCode {
    val requestBlock: HttpRequestBuilder.() -> Unit = {
        contentType(ContentType.Application.Json)
        header("Access-Control-Allow-Origin", "*")
        setBody(request)
    }
    println("request block ${requestBlock}")
    val response = when (SupportedHttpMethods.parse(method.uppercase())) {
        SupportedHttpMethods.GET -> jsonClient.get(endpoint, requestBlock)
        SupportedHttpMethods.POST -> jsonClient.post(endpoint, requestBlock)
        SupportedHttpMethods.PUT -> jsonClient.put(endpoint, requestBlock)
        SupportedHttpMethods.PATCH -> jsonClient.patch(endpoint, requestBlock)
        SupportedHttpMethods.DELETE -> jsonClient.delete(endpoint, requestBlock)
    }

    return response.status
}
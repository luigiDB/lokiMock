package api

import MockConfigurator
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
    jsonClient.delete(MockConfigurator.path) {    }
}
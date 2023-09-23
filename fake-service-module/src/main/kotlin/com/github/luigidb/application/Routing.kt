package com.github.luigidb.application

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

fun Application.configureRouting() {

    fun String.escapeString(): String {
        return this.replace("\"", "\\\"")
    }

    routing {
        get("/endpointUnderTest") {

            val client = HttpClient(CIO) {
                install(HttpTimeout)
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
            }

            runBlocking {
                val firstRequest: Deferred<String> = async {
                    client.get("http://0.0.0.0:9898/foo") {
                        timeout {
                            requestTimeoutMillis = 500
                        }
                    }.bodyAsText()
                }
                val secondRequest: Deferred<String> = async {
                    client.get("http://0.0.0.0:9898/bar") {
                        timeout {
                            requestTimeoutMillis = 500
                        }
                    }.bodyAsText()
                }
                val firstRequestContent: String = firstRequest.await()
                val secondRequestContent: String = secondRequest.await()

                client.close()
                val response = Json { prettyPrint = true }

                val json1 = response.parseToJsonElement(firstRequestContent).jsonObject
                val json2 = response.parseToJsonElement(secondRequestContent).jsonObject

                val mergedJson = json1.toMutableMap().apply {
                    putAll(json2)
                }

                val jsonObject = Json.encodeToString(JsonObject.serializer(), JsonObject(mergedJson))
                call.respond(jsonObject)
            }
        }
    }
}
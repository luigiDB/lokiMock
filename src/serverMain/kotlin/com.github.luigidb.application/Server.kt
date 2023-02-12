package com.github.luigidb.application

import MockConfigurator
import com.github.luigidb.application.mock.JavalinMock
import com.github.luigidb.application.mock.MockSetup
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.HTML

fun main() {

    val mockWrapper: MockSetup = JavalinMock(9898)

    embeddedServer(Netty, port = 8080, host = "localhost") {

        install(ContentNegotiation) {
            json()
        }

        install(CORS) {
            allowMethod(HttpMethod.Get)
            allowMethod(HttpMethod.Delete)
            anyHost() //TODO: to be removed
            allowHeader(HttpHeaders.ContentType)
        }

        install(Compression) {
            gzip()
        }

        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            static("/static") {
                resources()
            }

            route(MockConfigurator.path) {
                post() {
                    mockWrapper.startMock()
                    val mock: MockConfigurator = call.receive<MockConfigurator>()
                    call.application.environment.log.info("Received new Mock for [${mock.endpoint}]")
                    if(mockWrapper.addRestMock(mock.method, mock.endpoint, mock.request, mock.response)) {
                        call.respond(HttpStatusCode.OK)
                    } else {
                        call.respond(HttpStatusCode.BadRequest)
                    }
                }
                delete() {
                    call.application.environment.log.info("Restart mock server")
                    mockWrapper.invalidateMocks()
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }.start(wait = true)
}
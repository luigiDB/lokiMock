package com.github.luigidb.application

import com.github.luigidb.mock.JavalinMock
import com.github.luigidb.mock.MockSetup
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.random.Random

fun Application.configureRouting() {
    val mockWrapper: MockSetup = JavalinMock(9898)

    routing {
        singlePageApplication {
            useResources = true
            filesPath = "static-frontend"
            defaultPage = "index.html"
            useResources = true
        }

        get("/server/random") {
            call.respondText {
                randomStringByKotlinRandom(10)
            }
        }

        route(MockConfigurator.PATH) {
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
}

fun randomStringByKotlinRandom(length: Int): String {
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    return (1..length)
        .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
        .joinToString("")
}

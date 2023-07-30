package com.github.luigidb.application

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.random.Random

fun Application.configureRouting() {
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
    }
}

fun randomStringByKotlinRandom(length: Int): String {
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    return (1..length)
        .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
        .joinToString("")
}

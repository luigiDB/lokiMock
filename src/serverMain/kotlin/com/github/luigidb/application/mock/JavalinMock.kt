package com.github.luigidb.application.mock

import io.javalin.Javalin
import io.javalin.http.HandlerType

class JavalinMock : MockSetup {

    var app: Javalin? = null
    var isRunning = false

    override fun addRestMock(httpMethod: String, path: String, request: String, response: String): Boolean {
        return if (isRunning) {
            app?.addHandler(
                HandlerType.findByName(httpMethod),
                path
            ) { ctx ->
                ctx.result(response)
            }
            true
        } else {
            false
        }
    }
}


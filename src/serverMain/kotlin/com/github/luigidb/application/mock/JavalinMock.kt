package com.github.luigidb.application.mock

import io.javalin.Javalin
import io.javalin.http.HandlerType

class JavalinMock(private val mockPort: Int) : MockSetup {

    private var app: Javalin? = null

    override fun addRestMock(httpMethod: String, path: String, request: String, response: String): Boolean {
        return if (isServerRunning()) {
            app?.addHandler(
                HandlerType.findByName(httpMethod.uppercase()),
                path
            ) { ctx -> ctx.result(response) }
            true
        } else {
            false
        }
    }

    override fun startMock() {
        if(!isServerRunning()) {
            app = Javalin.create().start(mockPort)
        }
    }

    override fun invalidateMocks() {
        app?.close()
        app = null
        this.startMock()
    }

    private fun isServerRunning(): Boolean {
        return app?.jettyServer()?.started ?: false
    }

    /**
     * For subscriptions Javalin support the after method on handlers that can be used to get mock called events
     */
}


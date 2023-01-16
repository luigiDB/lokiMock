package com.github.luigidb.application.mock

import io.netty.handler.codec.http.HttpMethod

interface MockSetup {
    fun addRestMock(httpMethod: String, path: String, request: String, response: String): Boolean
}
package com.github.luigidb.application.mock

interface MockSetup {

    fun addRestMock(httpMethod: String, path: String, request: String, response: String): Boolean
    fun startMock()
    fun invalidateMocks()
}
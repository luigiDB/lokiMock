package com.github.luigidb.application

import kotlinx.html.*

fun HTML.index() {
    head {
        title("loki mock")
    }
    body {
        div {
            id = "root"
        }
        script(src = "/static/lokiMock.js") {}
    }
}

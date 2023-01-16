package ui

import mockConfigurator
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul

val App = FC<Props> {
    div {
        h2 {
            +"Request"
        }
        div {
            editableText {
                content = "sample"
            }
        }
        button {
            +"start"
        }
    }

    div {
        h2 {
            +"Mocks"
        }

        ul {
            li{
                mockConfigurator {
                    name = "sample name"
                    request = "sample request"
                    response = "sample response"
                }
            }
        }
    }
}

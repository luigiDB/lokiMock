package ui

import csstype.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.h4
import react.useState

external interface MockProp : Props {
    var method: String
    var response: String
    var request: String
    var endpoint: String
    var notifyUpdate: (String, String) -> Unit
}

val mockConfigurator = FC<MockProp>("a mock #$#$") { prop ->

    val (endpoint, _) = useState(prop.endpoint)

    val manageUpdate = { response: String ->
        println("recceived update on [$endpoint] with [$response]")
        prop.notifyUpdate(endpoint, response)
    }

    div {
        h3 {
            +"${prop.method.uppercase()} ${prop.endpoint}"
        }
        div {
            css {
                display = Display.flex
            }
            div {
                css {
                    float = Float.left
                    width = 50.pct
                }
                h4 { +"Request" }
                editableText {
                    content = prop.request
                }
            }
            div {
                css {
                    float = Float.left
                    flexGrow = number(1.0)
                }
                h4 { +"Response" }
                editableText {
                    content = prop.response
                    contentUpdate = manageUpdate
                }
            }
            div {
                css {
                    clear = Clear.both
                }
            }
        }
    }
}
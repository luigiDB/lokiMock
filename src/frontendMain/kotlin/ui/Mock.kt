import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.h4
import ui.editableText

external interface MockProp: Props {
    var response: String
    var request: String
    var name: String
}

val mockConfigurator = FC<MockProp> {prop ->
    h3 {
        + prop.name
    }
    div {
        div {
            h4{ + "Request"}
            editableText {
                content = prop.request
            }
        }
        div {
            h4{ + "Response"}
            editableText {
                content = prop.response
            }
        }
    }
}
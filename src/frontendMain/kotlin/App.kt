import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.ul

val App = FC<Props> {
    div {
        h2 {
            +"Request"
        }
        div {
            p {
                +" actual request"
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
        }
    }
}

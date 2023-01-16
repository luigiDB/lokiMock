package ui

import InitialStatus
import mockConfigurator
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.useState


val App = FC<Props> {
    //TODO: the initial setup need to be loaded from a static resource
    val initialStatus: InitialStatus by useState(InitialStatus())

    div {
        h2 {
            +"Request to ${initialStatus.endpoint}"
        }
        div {

            editableText {
                content = initialStatus.request
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
            initialStatus.mocks.forEach { mock ->
                li{
                    mockConfigurator {
                        name = mock.endpoint
                        request = mock.request
                        response = mock.response
                    }
                }
            }
        }
    }
}

package ui

import InitialLokiState
import LokiState
import MockConfigurator
import api.addMock
import api.restartMockServer
import csstype.LineStyle.Companion.solid
import emotion.react.css
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.useState

private val scope = MainScope()

val App = FC<Props> {
    //TODO: the initial setup need to be loaded from a static resource
    val (lokiState, setLokiState) = useState<LokiState>(InitialLokiState())

    div {
        h2 {
            +"Request to ${lokiState.endpoint}"
        }
        div {

            editableText {
                content = lokiState.request
            }
        }
        button {
            +"start"
            onClick = {
                scope.launch {
                    restartMockServer()
                    lokiState.mocks.forEach {
                        addMock(it)
                    }
                }
            }
        }
    }

    div {
        h2 {
            +"Mocks"
        }

        ul {
            lokiState.mocks.forEach { mock ->
                li {
                    css {
                        border = solid
                    }
                    mockConfigurator {
                        method = mock.method
                        endpoint = mock.endpoint
                        request = mock.request
                        response = mock.response
                        notifyUpdate = { endpoint, response ->
                            println("Received root update on [$endpoint] with [$response]")

                            setLokiState {oldState ->
                                val foundMock: MockConfigurator? = oldState.mocks.find { it.endpoint == endpoint }
                                foundMock?.response = response
                                oldState
                            }
                        }
                    }
                }
            }
        }
    }
}

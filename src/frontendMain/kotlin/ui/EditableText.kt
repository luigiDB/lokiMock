package ui

import org.w3c.dom.HTMLDivElement
import react.FC
import react.Props
import react.dom.events.FormEventHandler
import react.dom.html.ReactHTML.div
import react.useState

external interface TextProps : Props {
    var content: String
    var contentUpdate: (String) -> Unit
}

val editableText = FC<TextProps> { prop ->

    val (text, setText) = useState(prop.content)

    val changeHandler: FormEventHandler<HTMLDivElement> = { it ->
        val content = (it.target as HTMLDivElement).textContent ?: ""
        println("update on [$text] with [$content]")
        setText(content)
        prop.contentUpdate(content)
    }

    div {
        contentEditable = true

        onInput = changeHandler

        +text
    }
}

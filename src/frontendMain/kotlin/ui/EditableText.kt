package ui

import react.FC
import react.Props
import react.dom.html.ReactHTML.blockquote

external interface TextProps : Props {
    var content: String

}

val editableText = FC<TextProps> { prop ->
    blockquote {
        contentEditable = true

        + prop.content
    }
}

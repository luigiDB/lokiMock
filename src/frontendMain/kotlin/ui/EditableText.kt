package ui

import react.FC
import react.Props
import react.useState
import kotlin.js.JSON.stringify

external interface TextProps : Props {
    var content: String
    var contentUpdate: (String) -> Unit
}

val editableText = FC<TextProps> { prop ->

    val (text, setText) = useState(prop.content)

    JsonEditorModule.JsonEditor {
        value = text
        onChange = { content ->
            val stringifiedContent = stringify(content)
            println("Update [$stringifiedContent]")
            println("Update on fancy Json editor for [$text] with [$stringifiedContent]")
            setText(stringifiedContent)
            println("After text: [$text]")
            prop.contentUpdate(stringifiedContent)
        }
        mode = "code"
    }

}
package ui

import react.ComponentClass
import react.Props

@JsModule("jsoneditor-react/es/index")
@JsNonModule
internal external object JsonEditorModule {
    val JsonEditor: ComponentClass<JsonEditorProp>
}

external interface JsonEditorProp: Props {
    var value: String
    var onChange: (String) -> Unit
    var mode: String
}
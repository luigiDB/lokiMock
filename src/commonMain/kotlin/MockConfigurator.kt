import kotlinx.serialization.Serializable

@Serializable
data class MockConfigurator(
    val method: String,
    val endpoint: String,
    var request: String,
    var response: String) {

    companion object {
        const val path: String = "/mockConfiguration"
    }
}
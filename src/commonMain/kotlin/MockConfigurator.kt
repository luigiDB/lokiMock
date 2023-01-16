import kotlinx.serialization.Serializable

@Serializable
data class MockConfigurator(
    val endpoint: String,
    val request: String,
    val response: String) {

    companion object {
        const val path: String = "/mockConfiguration"
    }
}
import kotlinx.serialization.internal.throwMissingFieldException

enum class SupportedHttpMethods(val method: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    companion object {
        public fun parse(method: String): SupportedHttpMethods {
            return when(method.uppercase()) {
                GET.method -> GET
                POST.method -> POST
                PUT.method -> PUT
                PATCH.method -> PATCH
                DELETE.method -> DELETE
                else -> throw Exception("$method is unsupported")
            }
        }

        public val supportedHttpMethods: Collection<SupportedHttpMethods> = setOf(GET, POST, PUT, PATCH, DELETE)
    }


}
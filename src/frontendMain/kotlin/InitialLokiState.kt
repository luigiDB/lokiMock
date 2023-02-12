class InitialLokiState() : LokiState {
    override val endpointMethod: String = "POSt"
    override val endpoint:String = "http://localhost:7777/endpointUnderTest"
    override var request: String = """
        {
            id: "the id", 
            name: "name,
            address: {
                city: "city",
                state: "state"
            }
        }
    """.trimIndent()
    override val mocks: List<MockConfigurator> = listOf (
        MockConfigurator("GET", "endpoint1", "{id : \"request 1\"}", "{id : \"response 1\"}"),
        MockConfigurator("GET", "endpoint2", "{id : \"request 2\"}", "{id : \"response 2\"}")
    )
}
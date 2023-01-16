class InitialStatus() {
    val endpoint:String = "sample endpoint"
    val request: String = """
        {
            id: "the id", 
            name: "name,
            address: {
                city: "city",
                state: "state"
            }
        }k
    """.trimIndent()
    val mocks: List<MockConfigurator> = listOf (
        MockConfigurator("endpoint 1", "{id : \"request 1\"}", "{id : \"response 1\"}"),
        MockConfigurator("endpoint 2", "{id : \"request 2\"}", "{id : \"response 2\"}")
    )
}
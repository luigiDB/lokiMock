interface LokiState {
    val endpointMethod: String
    val endpoint: String
    var request: String
    val mocks: List<MockConfigurator>
}
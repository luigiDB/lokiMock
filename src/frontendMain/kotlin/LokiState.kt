interface LokiState {
    val endpoint: String
    var request: String
    val mocks: List<MockConfigurator>
}
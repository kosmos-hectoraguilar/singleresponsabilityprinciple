package shared

class DummySessionRepository : ISessionRepository {
    override fun insertUser(data: Map<String, Any>): Boolean = true
}
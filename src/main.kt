import shared.DummySessionRepository
import shared.ISession
import simple.Session

fun main() {
    val session = Session(DummySessionRepository())
    session.signup("usuario", "pass", ISession.CUSTOMER)
}
package srp_solution

import shared.ISessionRepository
import shared.ISession
import shared.ISession.Companion.CUSTOMER
import shared.ISession.Companion.PROVIDER

class SessionFacade(private val repository: ISessionRepository) : ISession {

    override fun signup(username: String, password: String, type: Int, otherData: Map<String, Any>?): Boolean {
        return when (type) {
            CUSTOMER -> CustomerSession(repository).signup(username, password, otherData)
            PROVIDER -> ProviderSession(repository).signup(username, password, otherData)
            else -> false
        }
    }
}


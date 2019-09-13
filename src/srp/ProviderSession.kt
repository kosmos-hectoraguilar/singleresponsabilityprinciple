package srp

import shared.ISession.Companion.PROVIDER
import shared.ISessionRepository

class ProviderSession(private val repository: ISessionRepository) {
    fun signup(username: String, password: String, otherData: Map<String, Any>?): Boolean {
        // TODO: Agregar validaciones

        val data = mutableMapOf<String, Any>()
        data[ISessionRepository.USERNAME_PARAM] = username
        data[ISessionRepository.PASSWORD_PARAM] = password
        data[ISessionRepository.TYPE_PARAM] = PROVIDER
        data.takeIf { !otherData.isNullOrEmpty() }?.putAll(otherData!!)

        return repository.insertUser(data)
    }
}
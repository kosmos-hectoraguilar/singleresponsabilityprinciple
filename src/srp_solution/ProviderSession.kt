package srp_solution

import shared.ISession.Companion.PROVIDER
import shared.ISessionRepository

class ProviderSession(private val repository: ISessionRepository) {
    companion object {
        private const val REGEX_USERNAME = "[a-z0-9_.]{5,}"
        private const val REGEX_PASSWORD = "^(?=.{5,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=]).*\$"
        private const val REGEX_PHONE = "[\\d]{10}"
    }

    fun signup(username: String, password: String, otherData: Map<String, Any>?): Boolean {
        return if (validation(username, password, otherData)) {
            val data = mutableMapOf<String, Any>()
            data[ISessionRepository.USERNAME_PARAM] = username
            data[ISessionRepository.PASSWORD_PARAM] = password
            data[ISessionRepository.TYPE_PARAM] = PROVIDER
            data.takeIf { !otherData.isNullOrEmpty() }?.putAll(otherData!!)

            repository.insertUser(data)
        } else {
            false
        }
    }

    private fun validation(username: String, password: String, otherData: Map<String, Any>?): Boolean =
        username.matches(Regex(REGEX_USERNAME))
                && password.matches(Regex(REGEX_PASSWORD))
                && otherData?.takeIf { it.contains(ISessionRepository.PHONE_PARAM) }?.get(ISessionRepository.PHONE_PARAM).toString().matches(
            Regex(REGEX_PHONE)
        )
}
package shared

interface ISessionRepository {
    companion object {
        const val USERNAME_PARAM = "username"
        const val PASSWORD_PARAM = "password"
        const val TYPE_PARAM = "type"
        const val PHONE_PARAM = "phone"
        const val AGE_PARAM = "age"
    }

    fun insertUser(data: Map<String, Any>): Boolean
}
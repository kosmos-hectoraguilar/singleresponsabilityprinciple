package shared

interface ISession {
    // Companion object funciona para definir atributos static.
    companion object {
        const val CUSTOMER = 1
        const val PROVIDER = 2
    }

    fun signup(username: String, password: String, type: Int, otherData: Map<String, Any>? = null): Boolean
}
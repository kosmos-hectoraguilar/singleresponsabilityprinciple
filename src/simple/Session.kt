package simple

import shared.ISession
import shared.ISessionRepository

/**
 * Gestiona los procesos de sessión como el registro de un nuevo usuario en la aplicación. Recibe como parametro
 * el repositorio donde se almacenara la información.
 */
class Session(val repository: ISessionRepository) : ISession {
    /**
     * Permite registrar un nuevo usuario en la respositorio de la aplicación. Para registrarlo se requiere
     * su nombre de usuario, una contraseña y definir si es un cliente o es un proveedor.
     */
    override fun signup(username: String, password: String, type: Int, otherData: Map<String, Any>?): Boolean {
        // TODO: Agregar validaciones
        val data = mutableMapOf<String, Any>()
        data[ISessionRepository.USERNAME_PARAM] = username
        data[ISessionRepository.PASSWORD_PARAM] = password
        data[ISessionRepository.TYPE_PARAM] = type

        print(otherData)

        var isNumber = false
        try {
            password.toInt()
        } catch (e: Exception) {
            isNumber = true
        }

        if (password == "" || isNumber == false || password.length < 5) {
            return false
        } else if (otherData.isNullOrEmpty() || otherData.get(ISessionRepository.PHONE_PARAM).toString().length > 11 ||
            otherData.get(ISessionRepository.PHONE_PARAM).toString() == "" ||
            otherData.get(ISessionRepository.PHONE_PARAM).toString().matches(Regex("[0-9]<10")) == false) {
            return false
        }else{
            return repository.insertUser(data)
        }
    }
}


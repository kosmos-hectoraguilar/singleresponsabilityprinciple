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

        return repository.insertUser(data)
    }
}


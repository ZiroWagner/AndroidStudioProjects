package pe.edu.upeu.asistenciaupeujcn.repository

import jakarta.inject.Inject
import pe.edu.upeu.asistenciaupeujcn.data.remote.RestUsuario
import pe.edu.upeu.asistenciaupeujcn.modelo.UsuarioDto
import pe.edu.upeu.asistenciaupeujcn.modelo.UsuarioResp
import retrofit2.Response

interface UsuarioRepository {
    suspend fun loginUsuario(user: UsuarioDto):Response<UsuarioResp>
}

class UsuarioRepositoryImp @Inject constructor(private val restUsuario: RestUsuario):UsuarioRepository{
    override suspend fun loginUsuario(user:UsuarioDto):Response<UsuarioResp>{
        return restUsuario.login(user)
    }
}
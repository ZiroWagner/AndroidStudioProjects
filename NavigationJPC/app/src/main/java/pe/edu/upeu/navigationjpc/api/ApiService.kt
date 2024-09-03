package pe.edu.upeu.navigationjpc.api

import pe.edu.upeu.navigationjpc.modelo.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<User>>
}
package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.CharacterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPI{
    @GET("/api/people/")
    fun getCharactersAsync(@Query("page") page: Int) : Deferred<Response<CharacterResponse>>

//    @GET("/users/{userId}")
//    fun getUser(@Path("userId") userID: String) : Deferred<Response<UserFullInfo>>
}
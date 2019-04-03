package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.Character
import br.pprojects.swapp.models.CharacterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface CharacterAPI{
    @GET("/api/people/")
    fun getCharactersAsync(@Query("page") page: Int) : Deferred<Response<CharacterResponse>>

    @GET("/api/people/{id}")
    fun getCharacterDetailsAsync(@Query("id") id: Int) : Deferred<Response<Character>>


    @POST("/favorite/{id}")
    fun updateFavoriteAsync(@Path("id") id: Int) : Deferred<Response<Character>>
}
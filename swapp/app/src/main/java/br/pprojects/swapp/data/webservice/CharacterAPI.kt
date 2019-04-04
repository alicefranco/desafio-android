package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface CharacterAPI{
    @GET("/api/people/")
    fun getCharactersAsync(@Query("page") page: Int) : Deferred<Response<CharacterResponse>>

    @GET("/api/people/{id}")
    fun getCharacterDetailsAsync(@Path("id") id: Int) : Deferred<Response<CharacterWS>>

    @POST("/favorite/{id}")
    fun updateFavoriteAsync(@Path("id") id: Int) : Deferred<Response<Character>>

}
package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.Character
import br.pprojects.swapp.models.CharacterResponse
import br.pprojects.swapp.models.Homeworld
import br.pprojects.swapp.models.Species
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface CharacterAPI{
    @GET("/api/people/")
    fun getCharactersAsync(@Query("page") page: Int) : Deferred<Response<CharacterResponse>>

    @GET("/api/people/{id}")
    fun getCharacterDetailsAsync(@Path("id") id: Int) : Deferred<Response<Character>>

    @POST("/favorite/{id}")
    fun updateFavoriteAsync(@Path("id") id: Int) : Deferred<Response<Character>>

    @GET("/api/species/{id}")
    fun getSpeciesDetailsAsync(@Path("id") id: Int) : Deferred<Response<Species>>

    @GET("/api/homeworld/{id}")
    fun getHomeworldDetailsAsync(@Path("id") id: Int) : Deferred<Response<Homeworld>>
}
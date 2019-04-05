package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.CharacterResponse
import br.pprojects.swapp.models.PlanetResponse
import br.pprojects.swapp.models.PlanetWS
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlanetAPI {

    @GET("/api/planets/")
    fun getPlanetsAsync(@Query("page") page: Int) : Deferred<Response<PlanetResponse>>

    @GET("/api/planets/{id}")
    fun getPlanetDetailsAsync(@Path("id") id: Int) : Deferred<Response<PlanetWS>>
}
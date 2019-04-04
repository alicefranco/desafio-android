package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.PlanetWS
import br.pprojects.swapp.models.SpeciesWS
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetAPI {

    @GET("/api/planets/{id}")
    fun getPlanetDetailsAsync(@Path("id") id: Int) : Deferred<Response<PlanetWS>>
}
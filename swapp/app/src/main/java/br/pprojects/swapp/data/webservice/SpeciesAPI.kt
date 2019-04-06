package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.SpeciesResponse
import br.pprojects.swapp.models.SpeciesWS
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpeciesAPI {

    @GET("/api/species/")
    fun getSpeciesAsync(@Query("page") page: Int) : Deferred<Response<SpeciesResponse>>

    @GET("/api/species/{id}")
    fun getSpeciesDetailsAsync(@Path("id") id: Int) : Deferred<Response<SpeciesWS>>
}
package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.SpeciesResponse
import br.pprojects.swapp.models.SpeciesWS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SpeciesWebservice {
    private val speciesResquestSwapi = BaseRequest.serviceSwapi.create(SpeciesAPI::class.java)

    fun getSpecies(page: Int, onSuccess: (SpeciesResponse?) -> Unit, onError: () -> Unit){
        GlobalScope.launch(Dispatchers.Main) {
            val request = speciesResquestSwapi.getSpeciesAsync(page)
            try {
                val response = request.await()
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    onError()
                }
            } catch (e: Throwable) {
                onError()
            }
        }
    }

    fun getSpeciesDetails(id: Int, onSuccess: (SpeciesWS?) -> Unit, onError: () -> Unit){
        GlobalScope.launch(Dispatchers.Main) {
            val request = speciesResquestSwapi.getSpeciesDetailsAsync(id)
            try {
                val response = request.await()
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    onError()
                }
            } catch (e: Throwable) {
                onError()
            }
        }
    }

}
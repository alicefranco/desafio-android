package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.PlanetWS
import br.pprojects.swapp.models.SpeciesWS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlanetWebservice {
    private val planetResquestSwapi = BaseRequest.serviceSwapi.create(PlanetAPI::class.java)

    fun getPlanet(id: Int, onSuccess: (PlanetWS?) -> Unit, onError: () -> Unit){
        GlobalScope.launch(Dispatchers.Main) {
            val request = planetResquestSwapi.getPlanetDetailsAsync(id)
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
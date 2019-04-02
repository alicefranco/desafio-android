package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.Character
import br.pprojects.swapp.models.CharacterResponse
import kotlinx.coroutines.*

class CharacterWebservice {
    private val characterRequestSwapi = BaseRequest.serviceSwapi.create(CharacterAPI::class.java)
    private val characterRequestPopCode = BaseRequest.servicePopCode.create(CharacterAPI::class.java)

    fun getCharacters(page: Int, onSuccess: (CharacterResponse?) -> Unit, onError: () -> Unit){
        GlobalScope.launch(Dispatchers.Main) {
            val request = characterRequestSwapi.getCharactersAsync(page)
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

    fun getCharacterDetails(id: Int, onSuccess: (Character?) -> Unit, onError: () -> Unit){
        GlobalScope.launch(Dispatchers.Main) {
            val request = characterRequestSwapi.getCharacterDetailsAsync(id)
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

    fun updateFavorite(id: Int, onSuccess: (Character?) -> Unit, onError: () -> Unit){
        GlobalScope.launch(Dispatchers.Main) {
            val request = characterRequestPopCode.updateFavoriteAsync(id)
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

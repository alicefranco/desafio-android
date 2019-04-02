package br.pprojects.swapp.data.webservice

import br.pprojects.swapp.models.CharacterResponse
import kotlinx.coroutines.*

class CharacterWebservice {
    private val characterRequest = BaseRequest.service.create(CharacterAPI::class.java)

    fun getCharacters(page: Int, onSuccess: (CharacterResponse?) -> Unit, onError: () -> Unit){
        GlobalScope.launch(Dispatchers.Main) {
            val request = characterRequest.getCharactersAsync(page)
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

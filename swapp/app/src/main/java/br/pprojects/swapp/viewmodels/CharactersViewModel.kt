package br.pprojects.swapp.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.repository.CharacterRepository

class CharactersViewModel : ViewModel(){
    private var characterRepository = CharacterRepository()
    var firstCharacters: LiveData<List<Character>>
    lateinit var characters: MutableLiveData<List<Character>>
    lateinit var favorites: LiveData<List<Character>>
    private var page = 1


    init{
        firstCharacters = MutableLiveData()
        favorites = MutableLiveData()
        getCharacters()
    }

    fun getCharacters() {
        if(page <=87 ){
            if (page == 1) {
                characterRepository.getCharacters(page)?.let {
                    firstCharacters = it
                }
                page++
            } else {
                if (!::characters.isInitialized)
                    characters = MutableLiveData()
                characters.value = characterRepository.getCharacters(page)?.value
                page++
            }
        }
    }

    fun updateFavorite(id: Int, value: Int){
        characterRepository.updateFavorite(id, value)
    }

    fun getAllFavorites()  {
        characterRepository.getAllFavorites()?.let{
            favorites = it
        }
    }


}
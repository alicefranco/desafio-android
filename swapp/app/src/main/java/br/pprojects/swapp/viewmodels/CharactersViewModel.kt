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
        getCharacters()
    }

    fun getCharacters() {
        if(page == 1){
            characterRepository.getCharacters(page)?.let{
                firstCharacters = it
            }
            page++
        }
        else{
            characters.value = characterRepository.getCharacters(page)?.value
            page++
        }
    }

    fun updateFavorite(id: Int, value: Boolean){
        if(!::favorites.isInitialized){
            favorites = MutableLiveData()
        }
        characterRepository.updateFavorite(id, value)
    }

    fun deleteFavorite(id: Int){}

    fun getAllFavorites() : LiveData<List<Character>> {
        if(!::favorites.isInitialized){
            favorites = MutableLiveData()
        }
        characterRepository.getAllFavorites()?.let{
            favorites = it
        }
        return favorites
    }
}
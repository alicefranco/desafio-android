package br.pprojects.swapp.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.repository.CharacterRepository

class CharacterDetailsViewModel : ViewModel() {
    var character: LiveData<Character> = MutableLiveData()
    private var characterRepository = CharacterRepository()

    fun getCharacterDetails(id: Int) {
        characterRepository.getCharacterDetails(id)?.let{
            character = it
        }
    }
}
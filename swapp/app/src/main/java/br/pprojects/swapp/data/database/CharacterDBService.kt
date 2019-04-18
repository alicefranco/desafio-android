package br.pprojects.swapp.data.database

import br.pprojects.swapp.App
import br.pprojects.swapp.models.Character
import kotlinx.coroutines.*

class CharacterDBService {
    private var characaterDao: CharacterDao?

    init{
        characaterDao = App.database?.characterDao()
    }

    fun insertCharacters(characters: List<Character>) {//onSuccess: (LiveData<List<Character>>) -> Unit, onError: () -> Unit) {
        GlobalScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){  characaterDao?.insertCharacters(characters)}
        }
    }

    fun updateFavorite(id: Int, value: Int) {//onSuccess: (LiveData<List<Character>>) -> Unit, onError: () -> Unit) {
        GlobalScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){  characaterDao?.updateFavorite(id, value)}
        }
    }

    fun updateCharacterDetails(character: Character) {//onSuccess: (LiveData<List<Character>>) -> Unit, onError: () -> Unit) {
        GlobalScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){  characaterDao?.updateCharacterDetails(character)}
        }
    }
}
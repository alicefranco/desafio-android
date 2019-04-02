package br.pprojects.swapp.repository

import android.arch.lifecycle.LiveData
import br.pprojects.swapp.App
import br.pprojects.swapp.data.database.CharacterDao
import br.pprojects.swapp.data.webservice.CharacterWebservice
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.models.CharacterWS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharacterRepository {
    private var characterWebservice: CharacterWebservice? = null
    private var characterDao: CharacterDao? = null

    init{
        characterWebservice = CharacterWebservice()
        characterDao = App.database?.CharacterDao()
    }

    fun getCharacters(page: Int) : LiveData<List<Character>>?{
        refreshCharacters(page)
        return characterDao?.getAllCharacters()
    }

    private fun refreshCharacters(page: Int){
        val charactersByPage = characterDao?.getCharactersByPage(page)
        if (charactersByPage.isNullOrEmpty())
            characterWebservice?.getCharacters(page, { response ->
                response?.results?.let { results ->
                    var resultsDb = arrayListOf<Character>()
                    results.forEach {
                        resultsDb.add(characterWsToCharacter(page, it))
                    }
                    characterDao?.insertCharacters(resultsDb.toList())
                }
            }, {
                //todo on error
            })
    }

    fun getCharacterDetails(id: Int) : LiveData<Character>?{
        //refreshCharacterDetails(id)
        return characterDao?.getCharacterDetails(id)
    }

    fun updateFavorite(id: Int, value: Boolean){
        if(value) refreshFavorite(id)
        characterDao?.updateFavorite(id, value)
    }

    fun refreshFavorite(id: Int){
        characterWebservice?.updateFavorite(id, {

        }, {

        })
    }

    fun getAllFavorites() : LiveData<List<Character>>?{
        return characterDao?.getAllFavorites()
    }


    private fun refreshCharacterDetails(id: Int){
//        val characterDetails = characterDao?.getCharacterDetails(id)
//
//        if(characterDetails?.value?.id == null){
//            characterWebservice?.getCharacterDetails(id,{
//                id
//            }, {
//
//            })
//        }
    }







    private fun characterWsToCharacter(page: Int, characterWS: CharacterWS) : Character{
        return Character().apply {
            this.pageReference = page
            this.name = characterWS.name
            //this.species = characterWS.species
            this.homeworld = characterWS.homeworld
            this.birthYear = characterWS.birthYear
            this.eyeColor = characterWS.eyeColor
            this.hairColor = characterWS.hairColor
            this.skinColor = characterWS.skinColor
            this.mass = characterWS.mass
            this.height = characterWS.height
            this.gender = characterWS.gender
        }
    }
}
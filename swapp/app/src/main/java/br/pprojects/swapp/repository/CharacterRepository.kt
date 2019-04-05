package br.pprojects.swapp.repository

import android.arch.lifecycle.LiveData
import br.pprojects.swapp.App
import br.pprojects.swapp.data.database.CharacterDao
import br.pprojects.swapp.data.webservice.CharacterWebservice
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.models.CharacterWS
import com.fasterxml.jackson.databind.ObjectMapper

class CharacterRepository {
    private var characterWebservice: CharacterWebservice? = null
    private var characterDao: CharacterDao? = null

    init{
        characterWebservice = CharacterWebservice()
        characterDao = App.database?.characterDao()
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
        refreshCharacterDetails(id)
        return characterDao?.getCharacterDetails(id)
    }

    fun updateFavorite(id: Int, value: Int){
        if(value == 1) refreshFavorite(id)
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
        characterDao?.getCharacterDetails(id)
        characterWebservice?.getCharacterDetails(id, { it ->
            var character = characterWsToCharacter(0, it ?: CharacterWS())
            character.let{
                it.id = id
                characterDao?.updateCharacterDetails(it)
            }
        }, {
        })
    }



    private fun getSpeciesId(url: String?) : Int{
        val id = url?.substringAfter("https://swapi.co/api/species/")?.replace("/", "") ?: ""
        return id.toInt()
    }

    private fun getHomeWorldId(url: String?) : String {
        val id = url?.substringAfter("https://swapi.co/api/planets/")?.replace("/", "") ?: ""
        return id
    }

    private fun characterWsToCharacter(page: Int, characterWS: CharacterWS) : Character{
        return Character().apply {
            this.pageReference = page
            this.name = characterWS.name
            this.birthYear = characterWS.birthYear
            this.eyeColor = characterWS.eyeColor
            this.hairColor = characterWS.hairColor
            this.skinColor = characterWS.skinColor
            this.mass = characterWS.mass
            this.height = characterWS.height
            this.gender = characterWS.gender

            characterWS.homeworld = getHomeWorldId(characterWS.homeworld)
            this.homeworld = characterWS.homeworld

            var speciesIds = arrayListOf<Int>()
            characterWS.species?.forEach { speciesIds.add(getSpeciesId(it))}
            this.species = speciesIds
        }
    }
}
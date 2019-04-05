package br.pprojects.swapp.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.models.Planet
import br.pprojects.swapp.models.Species
import br.pprojects.swapp.repository.CharacterRepository
import br.pprojects.swapp.repository.PlanetRepository
import br.pprojects.swapp.repository.SpeciesRepository

class CharacterDetailsViewModel : ViewModel() {
    var character: LiveData<Character> = MutableLiveData()
    var planet: LiveData<Planet> = MutableLiveData()
    //var species: MutableLiveData<List<Species>> = MutableLiveData()
    var species: LiveData<Species> = MutableLiveData()
    private var speciesTemp = ArrayList<Species>()
    private var characterRepository = CharacterRepository()
    private var planetRepository = PlanetRepository()
    private var speciesRepository = SpeciesRepository()



    fun getCharacterDetails(id: Int) {
        characterRepository.getCharacterDetails(id)?.let{
            character = it
        }
    }

    fun updateFavorite(id: Int, value: Int){
        characterRepository.updateFavorite(id, value)
    }

    fun getPlanet(id: Int){
        planetRepository.getPlanetDetails(id)?.let{
            planet = it
        }
    }

    fun getSpecies(ids: List<String>?){
//        ids?.forEach {id ->
//            speciesRepository.getSpeciesDetails(id.toInt()).let{ speciesItem ->
//                speciesItem?.value?.let{
//                    speciesTemp.add(it)
//                }
//                species.value = speciesTemp
//            }
//        }
        speciesRepository.getSpeciesDetails(ids?.get(0)?.toInt() ?: 0)?.let{
            species = it
        }
    }
}
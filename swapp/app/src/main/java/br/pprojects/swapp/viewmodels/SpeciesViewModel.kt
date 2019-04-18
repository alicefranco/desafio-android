package br.pprojects.swapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pprojects.swapp.models.Species
import br.pprojects.swapp.repository.SpeciesRepository


class SpeciesViewModel : ViewModel(){
    private var speciesRepository = SpeciesRepository()
    var firstSpecies: LiveData<List<Species>>
    lateinit var species: MutableLiveData<List<Species>>
    private var page = 1

    init{
        firstSpecies = MutableLiveData()
        getSpecies()
    }

    fun getSpecies() {
        if(page <=87 ){
            if (page == 1) {
                speciesRepository.getSpecies(page)?.let {
                    firstSpecies = it
                }
                page++
            } else {
                if (!::species.isInitialized)
                    species = MutableLiveData()
                species.value = speciesRepository.getSpecies(page)?.value
                page++
            }
        }
    }

}
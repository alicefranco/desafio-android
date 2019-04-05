package br.pprojects.swapp.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.pprojects.swapp.models.Character
import br.pprojects.swapp.models.Planet
import br.pprojects.swapp.repository.CharacterRepository
import br.pprojects.swapp.repository.PlanetRepository

class PlanetsViewModel : ViewModel(){
    private var planetRepository = PlanetRepository()
    var firstPlanets: LiveData<List<Planet>>
    lateinit var planets: MutableLiveData<List<Planet>>
    private var page = 1

    init{
        firstPlanets = MutableLiveData()
        getPlanets()
    }

    fun getPlanets() {
        if(page <=87 ){
            if (page == 1) {
                planetRepository.getPlanets(page)?.let {
                    firstPlanets = it
                }
                page++
            } else {
                if (!::planets.isInitialized)
                    planets = MutableLiveData()
                planets.value = planetRepository.getPlanets(page)?.value
                page++
            }
        }
    }

}
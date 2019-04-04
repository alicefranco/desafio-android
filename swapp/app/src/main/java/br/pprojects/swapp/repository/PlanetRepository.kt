package br.pprojects.swapp.repository

import android.arch.lifecycle.LiveData
import br.pprojects.swapp.App
import br.pprojects.swapp.data.database.CharacterDao
import br.pprojects.swapp.data.database.PlanetDao
import br.pprojects.swapp.data.webservice.PlanetWebservice
import br.pprojects.swapp.models.Planet
import br.pprojects.swapp.models.PlanetWS

class PlanetRepository{
    private var planetWebservice: PlanetWebservice? = null
    private var planetDao: PlanetDao? = null

    init {
        planetWebservice = PlanetWebservice()
        planetDao = App.database?.planetDao()
    }

    fun getPlanetDetails(id: Int) : LiveData<Planet>? {
        refreshPlanetDetails(id)
        return planetDao?.getPlanetDetails(id)
    }

    private fun refreshPlanetDetails(id: Int){
        planetWebservice?.getPlanet(id, { planetws ->
            planetws?.let{
                val planet = planetWStoPlanet(id, it)
                planetDao?.insert(planet)
            }
        },{})
    }

    private fun planetWStoPlanet(id: Int, planetWS: PlanetWS) : Planet{
        return Planet().apply {
            this.id = id
            this.name = planetWS.name
        }
    }

}
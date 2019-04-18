package br.pprojects.swapp.repository

import androidx.lifecycle.LiveData
import br.pprojects.swapp.App
import br.pprojects.swapp.data.database.PlanetDBService
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

    fun getPlanets(page: Int) : LiveData<List<Planet>>?{
        refreshPlanets(page)
        return planetDao?.getAllPlanets()
    }

    private fun refreshPlanets(page: Int){
        val planetsByPage = planetDao?.getPlanetsByPage(page)
        if (planetsByPage?.value.isNullOrEmpty())
            planetWebservice?.getPlanets(page, { response ->
                response?.results?.let { results ->
                    var resultsDb = arrayListOf<Planet>()
                    results.forEach {
                        resultsDb.add(planetWStoPlanetWithPage(page, it))
                    }
                    PlanetDBService().insertPlanets(resultsDb.toList())
                }
            }, {
                //todo on error
            })
    }


    fun getPlanetDetails(id: Int) : LiveData<Planet>? {
        refreshPlanetDetails(id)
        return planetDao?.getPlanetDetails(id)
    }

    private fun refreshPlanetDetails(id: Int){
        planetWebservice?.getPlanet(id, { planetws ->
            planetws?.let{
                val planet = planetWStoPlanet(id, it)
                PlanetDBService().insertPlanet(planet)
            }
        },{})
    }


    private fun planetWStoPlanet(id: Int, planetWS: PlanetWS) : Planet{
        return Planet().apply {
            this.id = id
            this.name = planetWS.name
            this.climate = planetWS.climate
            this.gravity = planetWS.gravity
            this.population = planetWS.population
            this.terrain = planetWS.terrain
            this.surfaceWater = planetWS.surfaceWater
        }
    }

    private fun planetWStoPlanetWithPage(page: Int, planetWS: PlanetWS) : Planet{
        return Planet().apply {
            this.pageReference = page
            this.name = planetWS.name
            this.climate = planetWS.climate
            this.gravity = planetWS.gravity
            this.population = planetWS.population
            this.terrain = planetWS.terrain
            this.surfaceWater = planetWS.surfaceWater.toString()
        }
    }

}
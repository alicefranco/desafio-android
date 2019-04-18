package br.pprojects.swapp.data.database

import br.pprojects.swapp.App
import br.pprojects.swapp.models.Planet
import kotlinx.coroutines.*

class PlanetDBService {
    private var planetDao: PlanetDao?

    init{
        planetDao = App.database?.planetDao()
    }

    fun insertPlanet(planet: Planet) {//onSuccess: (LiveData<List<Character>>) -> Unit, onError: () -> Unit) {
        GlobalScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){ planetDao?.insertPlanet(planet)}
        }
    }

    fun insertPlanets(planets: List<Planet>) {//onSuccess: (LiveData<List<Character>>) -> Unit, onError: () -> Unit) {
        GlobalScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){  planetDao?.insertPlanets(planets)}
        }
    }
}
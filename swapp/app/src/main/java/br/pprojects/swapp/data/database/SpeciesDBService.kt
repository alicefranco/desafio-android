package br.pprojects.swapp.data.database

import br.pprojects.swapp.App
import br.pprojects.swapp.models.Planet
import br.pprojects.swapp.models.Species
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpeciesDBService {
    private var speciesDao: SpeciesDao?

    init{
        speciesDao = App.database?.speciesDao()
    }

    fun insertSpecies(species: Species) {//onSuccess: (LiveData<List<Character>>) -> Unit, onError: () -> Unit) {
        GlobalScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){ speciesDao?.insertSpecies(species)}
        }
    }

    fun insertSpeciesList(species: List<Species>) {//onSuccess: (LiveData<List<Character>>) -> Unit, onError: () -> Unit) {
        GlobalScope.launch(Dispatchers.Main){
            withContext(Dispatchers.IO){  speciesDao?.insertSpeciesList(species)}
        }
    }
}
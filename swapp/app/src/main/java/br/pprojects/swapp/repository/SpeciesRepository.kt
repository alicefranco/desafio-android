package br.pprojects.swapp.repository

import android.arch.lifecycle.LiveData
import br.pprojects.swapp.App
import br.pprojects.swapp.data.database.SpeciesDao
import br.pprojects.swapp.data.webservice.SpeciesWebservice
import br.pprojects.swapp.models.Species
import br.pprojects.swapp.models.SpeciesWS


class SpeciesRepository {
    private var speciesWebservice: SpeciesWebservice? = null
    private var speciesDao: SpeciesDao? = null

    init {
        speciesWebservice = SpeciesWebservice()
        speciesDao = App.database?.speciesDao()
    }

    fun getSpeciesDetails(ids: List<String>?) : LiveData<List<Species>>? {
        ids?.forEach {
            refreshSpeciesDetails(it)
        }
        return speciesDao?.getSpeciesDetails(ids!!)
    }

    private fun refreshSpeciesDetails(id: String) {
        speciesWebservice?.getSpecies(id, { speciesws ->
            speciesws?.let {
                val species = speciesWStoSpecies(id, it)
                speciesDao?.insertSpecies(species)
            }
        }, {})
    }

    fun speciesWStoSpecies(id: String, species: SpeciesWS) : Species {
        return Species().apply{
            this.id = id
            this.name = species.name
        }
    }
}

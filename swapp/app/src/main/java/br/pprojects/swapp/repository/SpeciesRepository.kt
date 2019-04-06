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

    fun getSpecies(page: Int) : LiveData<List<Species>>?{
        refreshSpecies(page)
        return speciesDao?.getAllSpecies()
    }

    private fun refreshSpecies(page: Int){
        val speciesByPage = speciesDao?.getSpeciesByPage(page)
        if (speciesByPage.isNullOrEmpty())
            speciesWebservice?.getSpecies(page, { response ->
                response?.results?.let { results ->
                    var resultsDb = arrayListOf<Species>()
                    results.forEach {
                        resultsDb.add(speciesWStoSpeciesWithPage(page, it))
                    }
                    speciesDao?.insertSpeciesList(resultsDb.toList())
                }
            }, {
                //todo on error
            })
    }

    fun getSpeciesDetails(id: Int) : LiveData<Species>? {
        refreshSpeciesDetails(id)
        return speciesDao?.getSpeciesDetails(id)
    }

    private fun refreshSpeciesDetails(id: Int) {
        speciesWebservice?.getSpeciesDetails(id, { speciesws ->
            speciesws?.let {
                val species = speciesWStoSpecies(id, it)
                speciesDao?.insertSpecies(species)
            }
        }, {})
    }

    fun speciesWStoSpecies(id: Int, speciesws: SpeciesWS) : Species {
        return Species().apply{
            this.id = id
            this.name = speciesws.name
            this.averageHeight = speciesws.averageHeight
            this.averageLifespan = speciesws.averageLifespan
            this.classification = speciesws.classification
            this.eyeColors = speciesws.eyeColors
            this.hairColors = speciesws.hairColors
            this.skinColors = speciesws.skinColors
            this.designation = speciesws.language
            this.language = speciesws.language
        }
    }

    private fun speciesWStoSpeciesWithPage(page: Int, speciesws: SpeciesWS) : Species {
        return Species().apply {
            this.pageReference = page
            this.name = speciesws.name
            this.averageHeight = speciesws.averageHeight
            this.averageLifespan = speciesws.averageLifespan
            this.classification = speciesws.classification
            this.eyeColors = speciesws.eyeColors
            this.hairColors = speciesws.hairColors
            this.skinColors = speciesws.skinColors
            this.designation = speciesws.language
            this.language = speciesws.language
        }
    }
}

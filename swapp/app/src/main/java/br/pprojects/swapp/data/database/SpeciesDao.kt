package br.pprojects.swapp.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.pprojects.swapp.models.Species
import br.pprojects.swapp.models.SpeciesWS


@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species")
    fun getAllSpecies(): LiveData<List<Species>>

    @Query("SELECT * FROM species WHERE id == :id")
    fun getSpeciesDetails(id: Int): LiveData<Species>

    @Insert
    fun insertSpecies(species: Species)

}
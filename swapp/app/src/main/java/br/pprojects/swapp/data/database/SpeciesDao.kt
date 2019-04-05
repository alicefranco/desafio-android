package br.pprojects.swapp.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.support.design.circularreveal.CircularRevealHelper
import br.pprojects.swapp.models.Species
import br.pprojects.swapp.models.SpeciesWS


@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species")
    fun getAllSpecies(): LiveData<List<Species>>

    @Query("SELECT * FROM species WHERE id == :id")
    fun getSpeciesDetails(id: Int): LiveData<Species>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecies(species: Species)

}
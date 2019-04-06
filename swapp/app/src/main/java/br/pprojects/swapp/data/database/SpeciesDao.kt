package br.pprojects.swapp.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.support.design.circularreveal.CircularRevealHelper
import br.pprojects.swapp.models.Species



@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species")
    fun getAllSpecies(): LiveData<List<Species>>

    @Query("SELECT * FROM species WHERE pageReference == :page")
    fun getSpeciesByPage(page: Int): List<Species>

    @Query("SELECT * FROM species WHERE id == :id")
    fun getSpeciesDetails(id: Int): LiveData<Species>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecies(species: Species)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeciesList(characters: List<Species>)

}
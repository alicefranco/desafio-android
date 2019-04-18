package br.pprojects.swapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.pprojects.swapp.models.Species


@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species")
    fun getAllSpecies(): LiveData<List<Species>>

    @Query("SELECT * FROM species WHERE pageReference == :page")
    fun getSpeciesByPage(page: Int): LiveData<List<Species>>

    @Query("SELECT * FROM species WHERE id == :id")
    fun getSpeciesDetails(id: Int): LiveData<Species>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: Species)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeciesList(characters: List<Species>)

}
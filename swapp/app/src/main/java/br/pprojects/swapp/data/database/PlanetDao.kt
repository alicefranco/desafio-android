package br.pprojects.swapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.pprojects.swapp.models.Planet


@Dao
interface PlanetDao {
    @Query("SELECT * FROM planet")
    fun getAllPlanets(): LiveData<List<Planet>>

    @Query("SELECT * FROM planet WHERE pageReference == :page")
    fun getPlanetsByPage(page: Int): LiveData<List<Planet>>

    @Query("SELECT * FROM planet WHERE id == :id")
    fun getPlanetDetails(id: Int): LiveData<Planet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(planet: Planet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanets(characters: List<Planet>)
}
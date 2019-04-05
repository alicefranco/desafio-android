package br.pprojects.swapp.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.pprojects.swapp.models.Planet


@Dao
interface PlanetDao {
    @Query("SELECT * FROM planet")
    fun getAllPlanets(): LiveData<List<Planet>>

    @Query("SELECT * FROM planet WHERE pageReference == :page")
    fun getPlanetsByPage(page: Int): List<Planet>

    @Query("SELECT * FROM planet WHERE id == :id")
    fun getPlanetDetails(id: Int): LiveData<Planet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanet(planet: Planet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanets(characters: List<Planet>)
}
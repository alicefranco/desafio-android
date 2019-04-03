package br.pprojects.swapp.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import br.pprojects.swapp.models.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAllCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM character WHERE pageReference == :page")
    fun getCharactersByPage(page: Int): List<Character>

    @Insert
    fun insertCharacters(characters: List<Character>)

    @Query("SELECT * FROM character WHERE id == :id")
    fun getCharacterDetails(id: Int): LiveData<Character>

    @Query("UPDATE character SET isFavorite = :value WHERE id == :id")
    fun updateFavorite(id: Int, value: Boolean)

    @Query( "SELECT * FROM character WHERE isFavorite == 'true'")
    fun getAllFavorites() : LiveData<List<Character>>

}
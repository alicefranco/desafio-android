package br.pprojects.swapp.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.pprojects.swapp.models.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAllCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM character WHERE pageReference == :page")
    fun getCharactersByPage(page: Int): List<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<Character>)

    @Query("SELECT * FROM character WHERE id == :id")
    fun getCharacterDetails(id: Int): LiveData<Character>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCharacterDetails(character: Character)

    @Query("UPDATE character SET favorited = :value WHERE id == :id")
    fun updateFavorite(id: Int, value: Int)

    @Query("SELECT * FROM character WHERE favorited == 1")
    fun getAllFavorites() : LiveData<List<Character>>

}
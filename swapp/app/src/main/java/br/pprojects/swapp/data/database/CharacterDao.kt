package br.pprojects.swapp.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.pprojects.swapp.models.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAllCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM character WHERE pageReference == :page")
    fun getCharactersByPage(page: Int): List<Character>

    @Insert
    fun insertCharacters(characters: List<Character>)
}
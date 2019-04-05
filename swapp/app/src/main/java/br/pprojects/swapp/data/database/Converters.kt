package br.pprojects.swapp.data.database

import android.arch.persistence.room.TypeConverter
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

class Converters {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return ObjectMapper().readValue(value, object : TypeReference<List<String>>() {})
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        return ObjectMapper().writeValueAsString(list)
    }
}
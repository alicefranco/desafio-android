package br.pprojects.swapp.data.database

import androidx.room.TypeConverter
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return ObjectMapper().readValue(value, object : TypeReference<List<String>>() {})
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return ObjectMapper().writeValueAsString(list)
    }
}
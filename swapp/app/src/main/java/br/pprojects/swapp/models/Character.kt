package br.pprojects.swapp.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRawValue


@JsonIgnoreProperties(ignoreUnknown = true)
data class CharacterResponse(@JsonProperty("count") var count: Int? = -1,
                     @JsonProperty("next") var next: String?,
                     @JsonProperty("previous") var previous: String?,
                     @JsonProperty("results") var results: List<CharacterWS>)



@Entity
data class Character(@PrimaryKey(autoGenerate = true)
                     var id: Int? = null,
                     var pageReference: Int? = null,
                     var favorited: Int = 0,
                     var name: String? = "",
                     var gender: String = "",
                     var height: String = "",
                     var mass: String = "",
                     var hairColor: String = "",
                     var skinColor: String = "",
                     var eyeColor: String = "",
                     var birthYear: String = "",
                     //var species: List<String>? = null,
                     var homeworld: String = "")

@JsonIgnoreProperties(ignoreUnknown = true)
data class CharacterWS(@JsonProperty("id") var id: Int? = null,
                     @JsonProperty("name") var name: String? = "",
                     @JsonProperty("gender") var gender: String = "",
                     @JsonProperty("height") var height: String = "",
                     @JsonProperty("mass") var mass: String = "",
                     @JsonProperty("hair_color") var hairColor: String = "",
                     @JsonProperty("skin_color") var skinColor: String = "",
                     @JsonProperty("eye_color") var eyeColor: String = "",
                     @JsonProperty("birth_year") var birthYear: String = "",
                     //@JsonProperty("species") var species: List<String>? = null,
                     @JsonRawValue @JsonProperty("homeworld") var homeworld: String = "")


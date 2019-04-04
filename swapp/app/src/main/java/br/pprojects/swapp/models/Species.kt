package br.pprojects.swapp.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Entity
data class Species(@PrimaryKey(autoGenerate = true)
                   var id: Int? = null,
                   var name: String? = null)
//                   var classification: String,
//                   var designation: String,
//                   var averageHeight: String,
//                   var skinColors: String,
//                   var hairColors: String,
//                   var eyeColors: String,
//                   var averageLifespan: Int,
//                   var homeworld: String,
//                   var language: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SpeciesWS(@JsonProperty("name") var name: String? = null)
//                     @JsonProperty("classification") var classification: String,
//                     @JsonProperty("designation") var designation: String,
//                     @JsonProperty("average_height") var averageHeight: String,
//                     @JsonProperty("skin_colors") var skinColors: String,
//                     @JsonProperty("hair_colors") var hairColors: String,
//                     @JsonProperty("eye_colors") var eyeColors: String,
//                     @JsonProperty("eye_colors") var averageLifespan: Int,
//                     @JsonProperty("eye_colors") var homeworld: String,
//                     @JsonProperty("language") var language: String)
                   //var people: List<String>,




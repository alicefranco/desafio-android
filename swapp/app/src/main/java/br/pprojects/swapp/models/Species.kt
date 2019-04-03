package br.pprojects.swapp.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Species(@JsonProperty("name") var name: String,
                   @JsonProperty("classification") var classification: String,
                   @JsonProperty("designation") var designation: String,
                   @JsonProperty("average_height") var averageHeight: String,
                   @JsonProperty("skin_colors") var skinColors: String,
                   @JsonProperty("hair_colors") var hairColors: String,
                   @JsonProperty("eye_colors") var eyeColors: String,
                   @JsonProperty("eye_colors") var averageLifespan: Int,
                   @JsonProperty("eye_colors") var homeworld: String,
                   @JsonProperty("language") var language: String)
                   //var people: List<String>,




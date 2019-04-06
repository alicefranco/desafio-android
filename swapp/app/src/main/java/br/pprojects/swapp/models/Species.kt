package br.pprojects.swapp.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.lang.ref.PhantomReference


data class SpeciesResponse(@JsonProperty("count") var count: Int? = -1,
                          @JsonProperty("next") var next: String? = null,
                          @JsonProperty("previous") var previous: String? = null,
                          @JsonProperty("results") var results: List<SpeciesWS>? = null)

@Entity
data class Species(@PrimaryKey
                   var id: Int? = null,
                   var name: String? = null,
                   var pageReference: Int? = null,
                   var classification: String? = null,
                   var designation: String? = null,
                   var averageHeight: String? = null,
                   var skinColors: String? = null,
                   var hairColors: String? = null,
                   var eyeColors: String? = null,
                   var averageLifespan: String? = null,
                   var language: String? = null)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SpeciesWS(@JsonProperty("name") var name: String? = null,
                     @JsonProperty("classification") var classification: String? = null,
                     @JsonProperty("designation") var designation: String? = null,
                     @JsonProperty("average_height") var averageHeight: String? = null,
                     @JsonProperty("skin_colors") var skinColors: String? = null,
                     @JsonProperty("hair_colors") var hairColors: String? = null,
                     @JsonProperty("eye_colors") var eyeColors: String? = null,
                     @JsonProperty("average_lifespan") var averageLifespan: String? = null,
                     @JsonProperty("language") var language: String? = null)




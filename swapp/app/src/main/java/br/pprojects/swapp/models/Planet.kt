package br.pprojects.swapp.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class PlanetResponse(@JsonProperty("count") var count: Int? = -1,
                          @JsonProperty("next") var next: String? = null,
                          @JsonProperty("previous") var previous: String? = null,
                          @JsonProperty("results") var results: List<PlanetWS>? = null)

@Entity
data class Planet(@PrimaryKey(autoGenerate = true)
                  var id: Int? = null,
                  var name: String? = "",
                  var pageReference: Int? = null,
                  var climate: String? = "",
                  var gravity: String? = "",
                  var terrain: String? = "",
                  var surfaceWater: String? = "",
                  var population: String? = "")
                  //var residents: String? = "")

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlanetWS(@JsonProperty("name") var name: String? = "",
                    @JsonProperty("climate") var climate: String? = "",
                    @JsonProperty("gravity") var gravity: String? = "",
                    @JsonProperty("terrain") var terrain: String? = "",
                    @JsonProperty("surface_water") var surfaceWater: String? = "",
                    @JsonProperty("population") var population: String? = "")
                   // @JsonProperty("residents") var residents: String? = "")

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
                  var pageReference: Int? = null)

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlanetWS(@JsonProperty("name") var name: String? = "")

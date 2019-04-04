package br.pprojects.swapp.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@Entity
data class Planet(@PrimaryKey(autoGenerate = true)
                  var id: Int? = null,
                  var name: String? = "")

@JsonIgnoreProperties(ignoreUnknown = true)
data class PlanetWS(@JsonProperty("name") var name: String? = "")

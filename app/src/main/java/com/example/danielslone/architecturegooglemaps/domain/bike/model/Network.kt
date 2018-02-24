package com.example.danielslone.architecturegooglemaps.domain.bike.model

/**
 * Created by danielslone on 2/23/18.
 */
data class Network(val href: String,
                   val id: String,
                   val location: Location,
                   val name: String)

data class Location(val city: String,
                    val country: String,
                    val latitude: Double,
                    val longitude: Double)
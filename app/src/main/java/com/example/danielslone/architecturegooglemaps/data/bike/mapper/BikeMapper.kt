package com.example.danielslone.architecturegooglemaps.data.bike.mapper

import com.example.danielslone.architecturegooglemaps.data.bike.model.BikeShareCityResponse
import com.example.danielslone.architecturegooglemaps.domain.bike.model.Location
import com.example.danielslone.architecturegooglemaps.domain.bike.model.BikeShareCity

/**
 * Created by danielslone on 2/23/18.
 */
fun mapToNetworks(bikeShareCityResponse: BikeShareCityResponse): List<BikeShareCity> =
        bikeShareCityResponse.networks.map {
            it.run {
                BikeShareCity(href,
                              id,
                              location.run {
                                  Location(city, country, latitude, longitude)
                              },
                              name)
            }
    }
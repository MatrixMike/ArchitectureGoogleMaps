package com.example.danielslone.architecturegooglemaps.data.bike.mapper

import android.util.Log
import com.example.danielslone.architecturegooglemaps.data.bike.model.CityResponse
import com.example.danielslone.architecturegooglemaps.domain.bike.model.Location
import com.example.danielslone.architecturegooglemaps.domain.bike.model.Network

/**
 * Created by danielslone on 2/23/18.
 */
fun mapToNetworks(cityResponse: CityResponse): List<Network> {
    Log.d("mapper", cityResponse.networks.size.toString())
   return cityResponse.networks.map {
        it.run {
            Network(href,
                    id,
                    location.run {
                        Location(city, country, latitude, longitude)
                    },
                    name)
        }
    }
}
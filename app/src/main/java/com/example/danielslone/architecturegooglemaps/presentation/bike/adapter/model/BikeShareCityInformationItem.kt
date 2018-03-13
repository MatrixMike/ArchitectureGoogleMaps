package com.example.danielslone.architecturegooglemaps.presentation.bike.adapter.model

import com.example.danielslone.architecturegooglemaps.domain.bike.model.Location

/**
 * Created by danielslone on 2/24/18.
 */
data class BikeShareCityInformationItem(val bikeShareName: String,
                                        val city: String,
                                        val country: String,
                                        val location: Location)
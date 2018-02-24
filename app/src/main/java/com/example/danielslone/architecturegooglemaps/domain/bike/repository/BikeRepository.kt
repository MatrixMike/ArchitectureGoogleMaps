package com.example.danielslone.architecturegooglemaps.domain.bike.repository

import com.example.danielslone.architecturegooglemaps.domain.bike.model.Network
import io.reactivex.Single

/**
 * Created by danielslone on 2/23/18.
 */
interface BikeRepository {
    fun getNetworks(): Single<List<Network>>
}
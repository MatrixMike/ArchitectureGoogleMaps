package com.example.danielslone.architecturegooglemaps.domain.bike.usecase

import com.example.danielslone.architecturegooglemaps.application.annotation.Mockable
import com.example.danielslone.architecturegooglemaps.domain.bike.model.Network
import com.example.danielslone.architecturegooglemaps.domain.bike.repository.BikeRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by danielslone on 2/23/18.
 */
@Mockable
class GetNetworksUseCase @Inject constructor(private val bikeRepository: BikeRepository) {
    fun getNetworks(): Single<List<Network>> = bikeRepository.getNetworks()
}
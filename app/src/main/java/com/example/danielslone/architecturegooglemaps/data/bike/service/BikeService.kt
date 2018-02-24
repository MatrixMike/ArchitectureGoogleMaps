package com.example.danielslone.architecturegooglemaps.data.bike.service

import android.util.Log
import com.example.danielslone.architecturegooglemaps.data.bike.mapper.mapToNetworks
import com.example.danielslone.architecturegooglemaps.data.bike.model.CityResponse
import com.example.danielslone.architecturegooglemaps.domain.bike.repository.BikeRepository
import com.example.danielslone.architecturegooglemaps.domain.bike.model.Network
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

/**
 * Created by danielslone on 2/23/18.
 */
class BikeService @Inject constructor(retrofit: Retrofit) : BikeRepository {

    private val client: BikeClient

    init {
        client = retrofit.create(BikeClient::class.java)
    }

    override fun getNetworks(): Single<List<Network>> {
        Log.d("bike service", "bike service")
        return client.getNetworks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { mapToNetworks(it) }
                .doOnError{ Log.d("bike service", it.message.toString()) }
    }

    private interface BikeClient {

        @GET("networks")
        fun getNetworks(): Single<CityResponse>
    }
}
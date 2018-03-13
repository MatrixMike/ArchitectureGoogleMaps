package com.example.danielslone.architecturegooglemaps.data.bike.service

import android.util.Log
import com.example.danielslone.architecturegooglemaps.data.bike.mapper.mapToNetworks
import com.example.danielslone.architecturegooglemaps.data.bike.model.BikeShareCityResponse
import com.example.danielslone.architecturegooglemaps.domain.bike.repository.BikeRepository
import com.example.danielslone.architecturegooglemaps.domain.bike.model.BikeShareCity
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

    private companion object {
        val FILE_NAME = "BikeService"
    }

    private val client: BikeClient

    init {
        client = retrofit.create(BikeClient::class.java)
    }

    override fun getBikeShareCities(): Single<List<BikeShareCity>> {
        return client.getBikeShareCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { mapToNetworks(it) }
                .doOnError{ Log.d(FILE_NAME, it.message.toString()) }
    }

    private interface BikeClient {

        @GET("networks")
        fun getBikeShareCities(): Single<BikeShareCityResponse>
    }
}
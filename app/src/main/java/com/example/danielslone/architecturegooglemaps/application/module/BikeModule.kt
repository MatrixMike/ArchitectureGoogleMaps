package com.example.danielslone.architecturegooglemaps.application.module

import com.example.danielslone.architecturegooglemaps.data.bike.service.BikeService
import com.example.danielslone.architecturegooglemaps.domain.bike.repository.BikeRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by danielslone on 2/23/18.
 */
@Module
class BikeModule {

    private companion object {
        const val BIKE_RETROFIT = "BIKE_RETROFIT"
    }

    @Provides
    @Singleton
    @Named(BIKE_RETROFIT)
    fun provideBikeRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit = retrofitBuilder.baseUrl("https://api.citybik.es/v2/").build()

    @Provides
    @Singleton
    fun provideBikeRepository(@Named(BIKE_RETROFIT) retrofit: Retrofit): BikeRepository = BikeService(retrofit)
}
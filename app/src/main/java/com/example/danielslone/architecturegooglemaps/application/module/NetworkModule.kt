package com.example.danielslone.architecturegooglemaps.application.module

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by danielslone on 2/24/18.
 */
@Module
class NetworkModule {

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
}
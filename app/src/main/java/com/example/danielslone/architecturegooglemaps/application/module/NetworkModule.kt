package com.example.danielslone.architecturegooglemaps.application.module

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * Created by danielslone on 2/24/18.
 */
@Module
class NetworkModule {

    companion object {
        const val BASIC_HTTP_CLIENT = "basic_http_client"
        const val OAUTH_HTTP_CLIENT = "oauth_http_client"

        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val BEARER_PREFIX = "Bearer "

        private const val BASIC_AUTHENTICATOR = "basic_authenticator"
        private const val OAUTH_AUTHENTICATOR = "oauth_authenticator"

        private const val HTTP_UNAUTHORIZED = 401
    }

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
}
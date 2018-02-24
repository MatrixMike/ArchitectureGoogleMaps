package com.example.danielslone.architecturegooglemaps.application.injection.component

import com.example.danielslone.architecturegooglemaps.application.ArchitectureGoogleMapApplication
import com.example.danielslone.architecturegooglemaps.application.module.ApplicationModule
import com.example.danielslone.architecturegooglemaps.application.module.BikeModule
import com.example.danielslone.architecturegooglemaps.application.module.NetworkModule
import com.example.danielslone.architecturegooglemaps.domain.bike.repository.BikeRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by danielslone on 2/23/18.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
                             BikeModule::class,
                             NetworkModule::class))
interface ApplicationComponent {
    fun inject(application: ArchitectureGoogleMapApplication)

    fun getBikeRepository(): BikeRepository
}
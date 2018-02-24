package com.example.danielslone.architecturegooglemaps.application.injection.component

import com.example.danielslone.architecturegooglemaps.application.annotation.PerScreen
import com.example.danielslone.architecturegooglemaps.application.module.ActivityModule
import com.example.danielslone.architecturegooglemaps.presentation.bike.view.GoogleMapActivity
import dagger.Component

/**
 * Created by danielslone on 2/23/18.
 */
@PerScreen
@Component(dependencies = arrayOf(ApplicationComponent::class),
           modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(activity: GoogleMapActivity)
}
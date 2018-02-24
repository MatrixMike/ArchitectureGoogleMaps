package com.example.danielslone.architecturegooglemaps.application

import android.app.Application
import com.example.danielslone.architecturegooglemaps.R
import com.example.danielslone.architecturegooglemaps.application.injection.component.ApplicationComponent
import com.example.danielslone.architecturegooglemaps.application.injection.component.DaggerApplicationComponent
import com.example.danielslone.architecturegooglemaps.application.module.ApplicationModule
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by danielslone on 2/23/18.
 */
class ArchitectureGoogleMapApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        applicationComponent.inject(this)

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}
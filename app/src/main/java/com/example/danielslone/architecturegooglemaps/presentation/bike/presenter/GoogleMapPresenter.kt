package com.example.danielslone.architecturegooglemaps.presentation.bike.presenter

import com.example.danielslone.architecturegooglemaps.domain.bike.model.BikeShareCity
import com.example.danielslone.architecturegooglemaps.application.Presenter
import com.example.danielslone.architecturegooglemaps.domain.bike.usecase.GetBikeShareCitiesUseCase
import com.example.danielslone.architecturegooglemaps.presentation.bike.adapter.model.BikeShareCityInformationItem
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

/**
 * Created by danielslone on 2/23/18.
 */
class GoogleMapPresenter @Inject constructor(private val getBikeShareCitiesUseCase: GetBikeShareCitiesUseCase) : Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

    private var bikeShareMarkers: MutableList<MarkerOptions> = mutableListOf()

    // region Interfaces
    interface Display {
        fun showBikeShareCitiesList(bikeShareCityInformationItems: List<BikeShareCityInformationItem>)
        fun showBikeShareCitiesOnMap(bikeShareMarkers: List<MarkerOptions>)
        fun showLoading()
        fun hideLoading()
    }

    interface Router
    // endregion

    override fun onStart() {
        super.onStart()

        getBikeShareCities()
    }

    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    private fun getBikeShareCities() {
        getBikeShareCitiesUseCase.getBikeShareCities()
                .map { createBikeShareCityInformationItems(it) }
                .subscribe({ onNetworksSuccess(it) }, {  onNetworksFailure(it) })
    }

    private fun createBikeShareCityInformationItems(bikeShareCities: List<BikeShareCity>): List<BikeShareCityInformationItem> =
            bikeShareCities.map {
                BikeShareCityInformationItem(it.name,
                                             it.location.city,
                                             it.location.country,
                                             it.location)
            }

    private fun onNetworksSuccess(bikeShareCityInformationItems: List<BikeShareCityInformationItem>) {
        bikeShareCityInformationItems.forEach {
            val city = LatLng(it.location.latitude, it.location.longitude)
            val marker = MarkerOptions().position(city).title(it.bikeShareName)
            bikeShareMarkers.add(marker)
        }

        display.hideLoading()
        display.showBikeShareCitiesOnMap(bikeShareMarkers)
        display.showBikeShareCitiesList(bikeShareCityInformationItems)
    }

    private fun onNetworksFailure(throwable: Throwable) {

    }
}
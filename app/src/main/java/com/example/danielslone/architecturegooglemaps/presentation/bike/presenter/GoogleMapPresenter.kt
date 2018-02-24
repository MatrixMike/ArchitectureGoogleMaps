package com.example.danielslone.architecturegooglemaps.presentation.bike.presenter

import com.example.danielslone.architecturegooglemaps.domain.bike.model.BikeShareCity
import com.example.danielslone.architecturegooglemaps.application.Presenter
import com.example.danielslone.architecturegooglemaps.domain.bike.usecase.GetBikeShareCitiesUseCase
import com.example.danielslone.architecturegooglemaps.presentation.bike.adapter.model.CityInformationItem
import javax.inject.Inject

/**
 * Created by danielslone on 2/23/18.
 */
class GoogleMapPresenter @Inject constructor(private val networksUseCase: GetBikeShareCitiesUseCase) : Presenter {

    private lateinit var display: Display
    private lateinit var router: Router

    // region Interfaces
    interface Display {
        fun showNetworks(networks: List<CityInformationItem>)

        fun showLoading()
        fun hideLoading()
    }

    interface Router
    // endregion

    override fun onStart() {
        super.onStart()

        getNetworks()
    }

    override fun onResume() {
        super.onResume()
        getNetworks()
    }

    fun inject(display: Display, router: Router) {
        this.display = display
        this.router = router
    }

    private fun getNetworks() {
        networksUseCase.getBikeShareCities()
                .map { createNetworkList(it) }
                .subscribe({ onNetworksSuccess(it) }, {  onNetworksFailure(it) })
    }

    private fun createNetworkList(bikeShareCities: List<BikeShareCity>): List<CityInformationItem> =
            bikeShareCities.map {
                CityInformationItem(it.name,
                                    it.location.city + ", " + it.location.country)
            }

    private fun onNetworksSuccess(networks: List<CityInformationItem>) {
        display.showNetworks(networks)
    }

    private fun onNetworksFailure(throwable: Throwable) {

    }
}
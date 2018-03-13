package com.example.danielslone.architecturegooglemaps.presentation.bike.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.danielslone.architecturegooglemaps.R
import com.example.danielslone.architecturegooglemaps.application.BaseActivity
import com.example.danielslone.architecturegooglemaps.application.injection.component.ActivityComponent
import com.example.danielslone.architecturegooglemaps.presentation.bike.adapter.GoogleMapsAdapter
import com.example.danielslone.architecturegooglemaps.presentation.bike.adapter.model.BikeShareCityInformationItem
import com.example.danielslone.architecturegooglemaps.presentation.bike.presenter.GoogleMapPresenter
import kotlinx.android.synthetic.main.activity_google_maps.*
import javax.inject.Inject
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by danielslone on 2/23/18.
 */
class GoogleMapActivity : BaseActivity(), GoogleMapPresenter.Display, GoogleMapPresenter.Router, OnMapReadyCallback {

    @Inject
    override lateinit var presenter: GoogleMapPresenter

    private lateinit var googleMapsAdapter: GoogleMapsAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_google_maps)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupRecyclerView()
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.inject(this, this)
    }

    override fun showBikeShareCitiesList(bikeShareCityInformationItems: List<BikeShareCityInformationItem>) {
        googleMapsAdapter.bikeShareCityInformationRows = bikeShareCityInformationItems

        googleMapsAdapter.notifyDataSetChanged()
    }

    override fun showBikeShareCitiesOnMap(bikeShareMarkers: List<MarkerOptions>) {
        bikeShareMarkers.forEach {
            mMap.addMarker(it)
        }
    }

    override fun showLoading() {}

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    // region Private Functions
    private fun setupRecyclerView() {
        googleMapsAdapter = GoogleMapsAdapter(this)

        recyclerView = bikeShareCityRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = googleMapsAdapter
    }
    // endregion
}
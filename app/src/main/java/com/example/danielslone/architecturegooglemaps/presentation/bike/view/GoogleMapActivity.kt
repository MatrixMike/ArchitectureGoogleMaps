package com.example.danielslone.architecturegooglemaps.presentation.bike.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.danielslone.architecturegooglemaps.R
import com.example.danielslone.architecturegooglemaps.application.BaseActivity
import com.example.danielslone.architecturegooglemaps.application.injection.component.ActivityComponent
import com.example.danielslone.architecturegooglemaps.presentation.bike.adapter.GoogleMapsAdapter
import com.example.danielslone.architecturegooglemaps.presentation.bike.adapter.model.CityInformationItem
import com.example.danielslone.architecturegooglemaps.presentation.bike.presenter.GoogleMapPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by danielslone on 2/23/18.
 */
class GoogleMapActivity : BaseActivity(), GoogleMapPresenter.Display, GoogleMapPresenter.Router {

    @Inject
    override lateinit var presenter: GoogleMapPresenter

    private lateinit var googleMapsAdapter: GoogleMapsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    override fun inject(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
        presenter.inject(this, this)
    }

    override fun showNetworks(networks: List<CityInformationItem>) {
        googleMapsAdapter.cityInformationRows = networks

        googleMapsAdapter.notifyDataSetChanged()
    }

    override fun showLoading() { }

    override fun hideLoading() { }

    // region Private Functions
    private fun setupRecyclerView() {
        googleMapsAdapter = GoogleMapsAdapter(this)

        recyclerView = cityBikeRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = googleMapsAdapter
    }
    // endregion
}
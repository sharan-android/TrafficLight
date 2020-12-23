package com.app.trafficlights.views.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.app.trafficlights.utils.trafficview.TrafficSequence

/**
 * HomeViewModel to handle functionality in Home Page
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    //Variable for context
    private val context = application

    //Home repository instance
    private var repository = HomeRepository(context)

    /**
     * Used to animate the traffic lights
     * @param trafficSequence - Instance of the Traffic Sequence
     */
    fun animateLights(trafficSequence: TrafficSequence) =
        repository.startTrafficLights(trafficSequence)
}
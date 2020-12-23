package com.app.trafficlights.views.home

import android.content.Context
import com.app.trafficlights.utils.trafficview.TrafficSequence

/**
 * Repository class for Home view
 *
 * @param context of application
 */
class HomeRepository(private val context: Context) {

    /**
     * Used to start the traffic lights
     * @param trafficSequence - Instance of the TrafficSequence
     */
    fun startTrafficLights(trafficSequence: TrafficSequence) {
        trafficSequence.startSequence()
    }

}
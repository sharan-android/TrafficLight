package com.app.trafficlights.views.home

import android.graphics.PorterDuff
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.app.trafficlights.R
import com.app.trafficlights.utils.trafficview.TrafficLight
import com.app.trafficlights.utils.trafficview.TrafficSequence
import com.app.trafficlights.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Home Activity that is used to handle the TrafficLights view
 */
class HomeActivity : BaseActivity(), TrafficSequence.SequenceListener {

    // Instance of the Traffic Light to handle the Traffic lights state
    private val trafficLight: TrafficLight =
        TrafficLight()

    // Instance of the Traffic Sequence to handle the sequence for the lights
    private val trafficSequence: TrafficSequence =
        TrafficSequence()

    // Instance of the HomeViewModel
    private lateinit var homeViewModel: HomeViewModel

    /**
     * Overridden method used to get the layout id
     * @return view id for the home view
     */
    override fun getLayoutId(): Int = R.layout.activity_home

    /**
     * Overridden method used to initialize the views
     */
    override fun initViews() {
        initViewModel()
        initLightsView()
    }

    /**
     * Used to initialize the view model
     */
    private fun initViewModel() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    /**
     * Used to initialize the traffic lights logic
     */
    private fun initLightsView() {
        // Used to initialize the sequence listener
        trafficSequence.setSequenceListener(this)
        // Used to set the traffic light state
        setTrafficLightState(TrafficLight.TrafficLightState.RED)
        // Used to start the animation for the traffic lights
        homeViewModel.animateLights(trafficSequence)
    }

    /**
     * Used to set the traffic light state and update accordingly
     * @param state - TrafficLight state value
     */
    private fun setTrafficLightState(state: TrafficLight.TrafficLightState) {
        trafficLight.setState(state)
        changeLight(state)
    }

    /**
     * Used to update the traffic light view based on the state
     * @param state - TrafficLight state value
     */
    private fun changeLight(state: TrafficLight.TrafficLightState?) {
        when (state) {
            // Used to update the traffic lights view based on the state.
            // If state is red, make the green and yellow lights inactive, and the red light active
            TrafficLight.TrafficLightState.RED -> {
                updateYellowLightView(R.color.inactive_light)
                updateGreenLightView(R.color.inactive_light)
                updateRedLightView(R.color.red_light)
            }
            // Used to update the traffic lights view based on the state.
            // If the traffic light state is yellow, make the green and red lights inactive, and the red light active
            TrafficLight.TrafficLightState.YELLOW -> {
                updateYellowLightView(R.color.yellow_light)
                updateGreenLightView(R.color.inactive_light)
                updateRedLightView(R.color.inactive_light)
            }
            // Used to update the traffic lights view based on the state.
            // If the traffic light state is green, make the red and yellow lights inactive, and the red light active
            TrafficLight.TrafficLightState.GREEN -> {
                updateYellowLightView(R.color.inactive_light)
                updateGreenLightView(R.color.green_light)
                updateRedLightView(R.color.inactive_light)
            }
        }
    }

    /**
     * Used to update the yellow traffic light view
     * @param colorVal - Color value that needs to update on active or inactive
     */
    private fun updateYellowLightView(colorVal: Int) {
        iv_yellow.setColorFilter(
            ResourcesCompat.getColor(
                resources,
                colorVal,
                null
            ), PorterDuff.Mode.SRC_ATOP
        )
    }

    /**
     * Used to update the green traffic light view
     * @param colorVal - Color value that needs to update on active or inactive
     */
    private fun updateGreenLightView(colorVal: Int) {
        iv_green.setColorFilter(
            ResourcesCompat.getColor(
                resources,
                colorVal,
                null
            ), PorterDuff.Mode.SRC_ATOP
        )
    }

    /**
     * Used to update the red traffic light view
     * @param colorVal - Color value that needs to update on active or inactive
     */
    private fun updateRedLightView(colorVal: Int) {
        iv_red.setColorFilter(
            ResourcesCompat.getColor(
                resources,
                colorVal,
                null
            ), PorterDuff.Mode.SRC_ATOP
        )
    }

    /**
     * Overridden method of the SequenceListener to update the sequence based on the state
     */
    override fun sequenceWasTriggered(): TrafficLight.TrafficLightState? {
        var newState: TrafficLight.TrafficLightState? = null
        trafficLight.changeTrafficLight()?.let {
            newState = it
            changeLight(newState)
        }
        return newState
    }
}
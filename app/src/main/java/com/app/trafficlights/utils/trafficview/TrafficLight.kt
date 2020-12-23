package com.app.trafficlights.utils.trafficview

import java.util.*

/**
 * Class used to handle the Traffic Light state
 * RED, YELLOW or GREEN
 */
class TrafficLight {
    /**
     * Enums used to manage the traffic light state
     */
    enum class TrafficLightState {
        RED, YELLOW, GREEN
    }

    // Instance of the TrafficLightState
    private var state: TrafficLightState? = null
    // Instance of the TrafficLightState ArrayList
    private val trafficLightStates: ArrayList<TrafficLightState> = ArrayList()
    // Instance of the TrafficLightState ListIterator used to manage the lights
    private var listIterator: ListIterator<TrafficLightState>

    /**
     * Init block used to initialize the trafficLightStates arrayList
     * to manage the lights state
     */
    init {
        trafficLightStates.add(TrafficLightState.RED)
        trafficLightStates.add(TrafficLightState.YELLOW)
        trafficLightStates.add(TrafficLightState.GREEN)
        listIterator = trafficLightStates.listIterator()
    }

    /**
     * Used to reset the iterator to the beginning, and loop through
     * until the iterator reached to the specified state
     * @param state The state to set the iterator to
     */
    fun setState(state: TrafficLightState) {
        listIterator = trafficLightStates.listIterator()
        while (listIterator.hasNext()) {
            val tempState = listIterator.next()
            if (tempState == state) {
                this.state = tempState
                break
            }
        }
    }

    /**
     * Used to change the traffic lights based on the listIterator and the state
     */
    fun changeTrafficLight(): TrafficLightState? {
        return if (listIterator.hasNext()) {
            state = listIterator.next()
            state
        } else {
            listIterator = trafficLightStates.listIterator()
            state = listIterator.next()
            state
        }
    }
}
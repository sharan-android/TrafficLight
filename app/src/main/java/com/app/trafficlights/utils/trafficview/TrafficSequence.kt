package com.app.trafficlights.utils.trafficview

import android.os.Handler
import android.os.Looper
import com.app.trafficlights.utils.TrafficLightConstants

/**
 * Used to manage the Traffic light sequence based on the TrafficLight state
 */
class TrafficSequence {

    // Instance of the Handler used to manage the delay
    private var mHandler: Handler? = null

    // Instance of the SequenceListener used to manage the sequence of lights
    private var mListener: SequenceListener? = null

    /**
     * Used to set the sequence listener
     * @param listener - Instance of SequenceListener
     */
    fun setSequenceListener(listener: SequenceListener?) {
        mListener = listener
    }

    /**
     * SequenceListener used to manage the light view
     */
    interface SequenceListener {
        fun sequenceWasTriggered(): TrafficLight.TrafficLightState?
    }

    /**
     * Used to start the sequence
     */
    fun startSequence() {
        if (mHandler == null) {
            mHandler = Handler(Looper.getMainLooper())
            mHandler?.post(mRunnable)
        }
    }

    /**
     * Used to stop the sequence
     */
    fun stopSequence() {
        if (mHandler != null) {
            mHandler!!.removeCallbacks(mRunnable)
            mHandler = null
        }
    }

    /**
     * Runnable used to manage the TrafficLights delay
     */
    private val mRunnable: Runnable = object : Runnable {
        override fun run() {
            var newState: TrafficLight.TrafficLightState? = null
            if (mListener != null) {
                newState = mListener!!.sequenceWasTriggered()
            }
            when (newState) {
                TrafficLight.TrafficLightState.RED -> mHandler?.postDelayed(
                    this,
                    TrafficLightConstants.RED_DELAY
                )
                TrafficLight.TrafficLightState.GREEN -> mHandler?.postDelayed(
                    this,
                    TrafficLightConstants.GREEN_DELAY
                )
                TrafficLight.TrafficLightState.YELLOW -> mHandler?.postDelayed(
                    this,
                    TrafficLightConstants.YELLOW_DELAY
                )
            }
        }
    }
}
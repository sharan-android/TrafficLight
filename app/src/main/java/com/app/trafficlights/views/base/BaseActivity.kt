package com.app.trafficlights.views.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Base class for all Activities.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews()
    }

    /**
     * Used to get the layout id for the view
     * @return view id
     */
    abstract fun getLayoutId(): Int

    /**
     * Used to initialize the views
     */
    abstract fun initViews()
}
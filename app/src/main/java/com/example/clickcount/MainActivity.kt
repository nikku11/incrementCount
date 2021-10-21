package com.example.clickcount

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

public class MainActivity() : AppCompatActivity() {
    // Variables
    var tvButtonCount: TextView? = null
    var tvBackgroundCount: TextView? = null
    var buttonClick: Button? = null

    var intButtonClickCount: Int = 0
    var strButtonClickCount: String = ""
    var intBackgroundCount: Int = 0
    var strBackgroundCount: String = ""
    var sharedPreference:SharedPreferenceHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
//        checkOrientation()

    }

    override fun onStart() {
        super.onStart()
//        checkOrientation()
    }

    private fun init() {
        sharedPreference = SharedPreferenceHelper(this)
        tvButtonCount = findViewById(R.id.a_main_tv_button_counter) as TextView
        tvBackgroundCount = findViewById(R.id.a_main_tv_background_counter) as TextView
        buttonClick = findViewById(R.id.a_main_b_click_counter) as Button
        buttonClick!!.setOnClickListener() {
            intButtonClickCount = ++intButtonClickCount
            strButtonClickCount = intButtonClickCount.toString()
            tvButtonCount!!.setText(strButtonClickCount)
        }
//        checkOrientation()
        setButtonValues()
        setBackgroundValues()
    }

    private fun setButtonValues() {
        if (sharedPreference!!.getButtonCount(sharedPreference!!.BUTTON_KEY) != null) {
            strButtonClickCount = sharedPreference!!.getButtonCount(sharedPreference!!.BUTTON_KEY).toString()
            intButtonClickCount = strButtonClickCount.toInt()
            tvButtonCount!!.setText(strButtonClickCount)
        }
    }

    private fun setBackgroundValues() {
        if (sharedPreference?.BACKGROUND_KEY?.let { sharedPreference?.getBackgroundCount(it) } != null) {
            strBackgroundCount = sharedPreference!!.getBackgroundCount(sharedPreference!!.BACKGROUND_KEY).toString()
            intBackgroundCount = strBackgroundCount.toInt()
            tvBackgroundCount!!.setText(strBackgroundCount)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.layout_land)
            init()
            tvButtonCount!!.setText(strButtonClickCount)
            tvBackgroundCount!!.setText(strBackgroundCount)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main)
            init()
            tvButtonCount!!.setText(strButtonClickCount)
            tvBackgroundCount!!.setText(strBackgroundCount)
        }
    }
//    fun checkOrientation(){
//     val orientation = resources.configuration.orientation
//    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//        setContentView(R.layout.layout_land)
//            init()
//            tvButtonCount!!.setText(strButtonClickCount)
//            tvBackgroundCount!!.setText(strBackgroundCount)
//    } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//        setContentView(R.layout.activity_main)
//            init()
//            tvButtonCount!!.setText(strButtonClickCount)
//            tvBackgroundCount!!.setText(strBackgroundCount)
//    }
//
//
//}


    override fun onResume() {
        super.onResume()
//        checkOrientation()
        setButtonValues()
        setBackgroundValues()
    }

    override fun onPause() {
        super.onPause()
//        checkOrientation()
        sharedPreference!!.setButtonCount(sharedPreference!!.BUTTON_KEY, strButtonClickCount )
        intBackgroundCount = ++intBackgroundCount
        strBackgroundCount = intBackgroundCount.toString()
        tvBackgroundCount!!.setText(strBackgroundCount)
        sharedPreference!!.setBackgroundCount(sharedPreference!!.BACKGROUND_KEY, strBackgroundCount)
    }

    override fun onStop() {
        super.onStop()
//        checkOrientation()
        sharedPreference!!.setButtonCount(sharedPreference!!.BUTTON_KEY, strButtonClickCount )
        sharedPreference!!.setBackgroundCount(sharedPreference!!.BACKGROUND_KEY, strBackgroundCount )
    }

    override fun onDestroy() {
        super.onDestroy()
//        checkOrientation()
    }
}



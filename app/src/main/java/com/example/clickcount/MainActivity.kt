package com.example.clickcount

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.NumberFormatException

public class MainActivity() : AppCompatActivity() {

    // Variables
    var tvButtonCount: TextView? = null
    var tvBackgroundCount: TextView? = null
    var buttonClick: Button? = null

    var intButtonClickCount: Int = 0
    var strButtonClickCount: String = ""
    var intBackgroundCount: Int = 0
    var strBackgroundCount: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        tvButtonCount = findViewById(R.id.tv_button_count) as TextView
        tvBackgroundCount = findViewById(R.id.tv_background_count) as TextView
        buttonClick = findViewById(R.id.btn) as Button

        setButtonValues()
        setBackgroundValues()
        onClick()
    }

    private fun setButtonValues() {
        if (getSharedPreference("BUTTON", "button") != "button") {
            strButtonClickCount = getSharedPreference("BUTTON", "button")
            intButtonClickCount = strButtonClickCount.toInt()
            tvButtonCount!!.setText(strButtonClickCount)
        }
    }

    private fun setBackgroundValues() {
        if ( getSharedPreference("BACKGROUND", "background") != "background") {
            strBackgroundCount = getSharedPreference("BACKGROUND", "background")
            intBackgroundCount = strBackgroundCount.toInt()
            tvBackgroundCount!!.setText(strBackgroundCount)
        }
    }

    private fun onClick() {
        buttonClick?.setOnClickListener() {
            intButtonClickCount = ++ intButtonClickCount
            strButtonClickCount = intButtonClickCount.toString()
            tvButtonCount!!.setText(strButtonClickCount)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.layout_land)
            init()
            tvButtonCount?.setText(strButtonClickCount)
            tvBackgroundCount?.setText(strBackgroundCount)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main)
            init()
            tvButtonCount?.setText(strButtonClickCount)
            tvBackgroundCount?.setText(strBackgroundCount)
        }
    }

    override fun onResume() {
        super.onResume()
        setButtonValues()
        setBackgroundValues()
    }

    override fun onPause() {
        super.onPause()
        setSharedPreference("BUTTON", "button", strButtonClickCount)
        intBackgroundCount = ++intBackgroundCount
        strBackgroundCount = intBackgroundCount.toString()
        tvBackgroundCount!!.setText(strBackgroundCount)
        setSharedPreference("BACKGROUND", "background", strBackgroundCount)
    }

    override fun onStop() {
        super.onStop()
        setSharedPreference("BUTTON", "button", strButtonClickCount)
        setSharedPreference("BACKGROUND", "background", strBackgroundCount)
    }


    private fun getSharedPreference(prefsName: String, key: String): String {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            ?.getString(key, "0")?.let { return it }
        return "Preference doesn't exist."
    }

    private fun setSharedPreference(prefsName: String, key: String, value: String?) {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            .edit().apply { putString(key, value); apply() }
    }
}
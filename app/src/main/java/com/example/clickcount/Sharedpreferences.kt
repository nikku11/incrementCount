package com.example.clickcount
import android.content.Context
import android.content.SharedPreferences

open class SharedPreferenceHelper( context: Context) {
    private val PREFS_NAME = "kotlincodes"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val BUTTON_KEY = "button"
    val BACKGROUND_KEY = "background"

    fun setButtonCount(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getButtonCount(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun setBackgroundCount(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getBackgroundCount(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.commit()
    }
}




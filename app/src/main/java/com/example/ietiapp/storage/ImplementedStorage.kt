package com.example.ietiapp.storage

import android.content.SharedPreferences


class ImplementedStorage: Storage {

    private val TOKEN_KEY:String = "Token_key"
    private var sharedPreferences: SharedPreferences? = null

    fun SharedPreferencesStorage(sharedPreferences: SharedPreferences?) {
        this.sharedPreferences = sharedPreferences
    }

    override fun saveToken(token: String) {
        sharedPreferences!!.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getToken(): String {
        return sharedPreferences!!.getString(TOKEN_KEY, "")!!
    }
}
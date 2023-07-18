package com.testapp.mckinleytest.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.testapp.mckinleytest.util.Constants.AUTH_KEY
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreferences(private val dataStore:DataStore<Preferences>) {

    suspend fun saveAuthToken(loginToken:String){
        dataStore.edit { pref ->
            pref[AUTH_KEY] = setOf(loginToken)
        }
    }

}
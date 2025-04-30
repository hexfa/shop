package com.example.shop.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shop.utils.Constants.DATASTORE_NAME
import javax.inject.Inject


private val Context.datastore :DataStore<Preferences> by preferencesDataStore(name=DATASTORE_NAME)

class DataStoreRepositoryImp @Inject constructor(
    private val context: Context
):DataStoreRepository{
    override suspend fun putString(key: String, value: String) {
        val preferencesKey= stringPreferencesKey(key)
        context.datastore.edit { preferences->
            preferences[preferencesKey]=value
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        val preferencesKey= intPreferencesKey(key)
        context.datastore.edit { preferences->
            preferences[preferencesKey]=value
        }    }

    override suspend fun getString(key: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getInt(key: String): Int {
        TODO("Not yet implemented")
    }
}
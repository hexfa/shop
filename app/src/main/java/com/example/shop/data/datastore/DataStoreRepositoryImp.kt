package com.example.shop.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.shop.utils.Constants.DATASTORE_NAME
import javax.inject.Inject


private val Context.datastore :DataStore<Preferences> by preferencesDataStore(name=DATASTORE_NAME)

class DataStoreRepositoryImp @Inject constructor(
    private val context: Context
):DataStoreRepository{
    override suspend fun putString(key: String, value: String) {
        TODO("Not yet implemented")
    }

    override suspend fun putInt(key: String, value: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getString(key: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getInt(key: String): Int {
        TODO("Not yet implemented")
    }
}
package com.example.shop.data.datastore

interface DataStoreRepository {
    suspend fun putString(key:String,value:String)
}
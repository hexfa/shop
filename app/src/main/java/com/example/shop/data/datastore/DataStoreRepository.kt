package com.example.shop.data.datastore

interface DataStoreRepository {
    suspend fun putString(key:String,value:String)
    suspend fun putInt(key:String,value:String)
    suspend fun getString(key:String):String

}
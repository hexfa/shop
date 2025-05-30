package com.example.shop.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LocalUtils {

    fun setLocale(context: Context,language:String)= updateResource(context,language)
    private fun updateResource(context: Context,language:String){
        context.resources.apply {
            val local=Locale(language)
            val config= Configuration(configuration)

            context.createConfigurationContext(configuration)
            Locale.setDefault(local)
            config.setLocale(local)
            context.resources.updateConfiguration(config,displayMetrics)
        }
    }
}
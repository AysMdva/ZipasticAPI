package com.aysmdva.zipasticapi.network

import com.aysmdva.zipasticapi.Constants
import com.aysmdva.zipasticapi.api.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val api: API

    init {
        val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(API::class.java)
    }

    companion object {
        var service: RetrofitService? = null
            get() {
                if (field == null) {
                    field = RetrofitService()
                }
                return field
            }
            private set
    }


    fun getAPI(): API {
        return api
    }
}
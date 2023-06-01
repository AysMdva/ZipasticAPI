package com.aysmdva.zipasticapi.api

import com.aysmdva.zipasticapi.model.CountryDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("{id}")
    fun getCountryDTO(@Path("id") searchByZip :String) : Call<CountryDTO>
}
package com.aysmdva.zipasticapi.model

import com.google.gson.annotations.SerializedName

class CountryDTO(
    @SerializedName("country") val countryName: String,
    @SerializedName("state") val countryState: String,
    @SerializedName("city") val countryCity: String
)
package br.com.movieapp.framework.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductionCountriesItem(
    @SerializedName("iso")
    val iso: String = "",
    @SerializedName("name")
    val name: String = "")
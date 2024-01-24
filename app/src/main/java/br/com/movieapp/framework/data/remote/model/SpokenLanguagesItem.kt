package br.com.movieapp.framework.data.remote.model

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("iso")
    val iso: String = "",
    @SerializedName("english_name")
    val englishName: String = "")
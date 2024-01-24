package br.com.movieapp.framework.data.remote.response

import br.com.movieapp.framework.data.remote.model.ResultsItem
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<ResultsItem>?,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)
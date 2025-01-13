package br.com.movieapp.core.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val genres: List<String>?,
    val overview: String?,
    val voteAverage: Double = 0.0,
    val backdropPathUrl: String = "",
    val releaseDate: String?,
    val duration: Int = 0,
    val voteCount: Int = 0
)

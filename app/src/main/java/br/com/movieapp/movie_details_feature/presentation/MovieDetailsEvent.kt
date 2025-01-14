package br.com.movieapp.movie_details_feature.presentation

sealed class MovieDetailsEvent {
    data class GetMovieDetail(val movieId: Int): MovieDetailsEvent()
}

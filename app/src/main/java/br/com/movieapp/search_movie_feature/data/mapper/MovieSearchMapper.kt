package br.com.movieapp.search_movie_feature.data.mapper

import br.com.movieapp.core.data.remote.model.MovieResult
import br.com.movieapp.core.data.remote.model.SearchResultsIem
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.core.util.toBackdropUrl

fun List<SearchResultsIem>.toMovieSearch() = map {movieResult ->
    MovieSearch (
        id = movieResult.id,
        voteAverage = movieResult.voteAverage,
        imageUrl = movieResult?.posterPath?.toBackdropUrl() ?: ""
    )
}
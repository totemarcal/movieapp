package br.com.movieapp.movie_details_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMoviesDetails(movieId: Int) : MovieDetails
    suspend fun getMoviesSimilar(movieId: Int, pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}
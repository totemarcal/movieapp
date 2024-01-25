package br.com.movieapp.core.data.remote

import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.data.remote.response.MovieSearchResponse
import br.com.movieapp.core.data.remote.response.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResponse

    @GET("search/multi")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("query") query: String
    ): MovieSearchResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getPopularMovies(
        @Query("movie_id") movieId: Int,
        @Query("page") page: Int
    ): MovieResponse

}
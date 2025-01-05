package br.com.movieapp.search_movie_feature.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.response.MovieSearchResponse
import br.com.movieapp.core.paging.MovieSearchPagingSource
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private  val service: MovieService
) : MovieSearchRemoteDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query = query, remoteDatasource = this)
    }

    override suspend fun getSearchMovies(page: Int, query: String): MovieSearchResponse {
        return service.seacrhMovies(page, query)
    }
}
package br.com.movieapp.movie_details_feature.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.movie_details_feature.data.repository.MovieDetailsRepositoryImpl
import br.com.movieapp.movie_details_feature.data.source.MovieDetailsRemoteDataSourceImpl
import br.com.movieapp.movie_details_feature.domain.repository.MovieDetailsRepository
import br.com.movieapp.movie_details_feature.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.movie_details_feature.domain.usercase.GetMovieDetailsUseCase
import br.com.movieapp.movie_details_feature.domain.usercase.GetMovieDetailsUseCaseImpl
import br.com.movieapp.movie_popular_feature.data.repository.MoviePopularRepositoryImpl
import br.com.movieapp.movie_popular_feature.data.source.MoviePopularRemoteDataSourceImpl
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailsRemoteDataSource {
        return MovieDetailsRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(remoteDataSource: MovieDetailsRemoteDataSource): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(repository = repository)
    }
}
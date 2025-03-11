package br.com.movieapp.movie_details_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_details_feature.domain.usercase.GetMovieDetailsUseCase
import br.com.movieapp.movie_details_feature.presentation.state.MovieDetailState
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,

    ) : ViewModel() {
    var uiState by mutableStateOf(MovieDetailState())
        private set

    fun checkedFavorite(checkedFavorite: MovieDetailsEvent.CheckedFavorite) {
        event(checkedFavorite)
    }
    fun getMovieDetail(getMovieDetail: MovieDetailsEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    fun onAddFavorite(movie : Movie) {
        if (uiState.iconColor == Color.White) {
            event(MovieDetailsEvent.AddFavorite(movie = movie))
        } else {
            event(MovieDetailsEvent.RemoveFavorite(movie = movie))
        }
    }
    private fun event(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.AddFavorite -> {
                viewModelScope.launch {
                    addMovieFavoriteUseCase.invoke(params = AddMovieFavoriteUseCase.Params(
                        movie = event.movie
                    )).collectLatest {result ->
                        when(result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.Red)
                            }
                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Erro ao favoritar filme")
                            }
                            is ResultData.Loading -> {}
                        }
                    }
                }
            }
            is MovieDetailsEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isMovieFavoriteUseCase.invoke(
                        params = IsMovieFavoriteUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collectLatest { result ->
                        when(result) {
                            is ResultData.Success -> {
                                uiState = if (result.data == true) {
                                    uiState.copy(iconColor = Color.Red)
                                } else {
                                    uiState.copy(iconColor = Color.White)
                                }
                            }
                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Erro carregar favoritar filme")
                            }
                            is ResultData.Loading -> {}
                        }
                    }
                }
            }
            is MovieDetailsEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    deleteMovieFavoriteUseCase.invoke(params = DeleteMovieFavoriteUseCase.Params(
                        movie = event.movie
                    )).collectLatest {result ->
                        when(result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.White)
                            }
                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Erro ao desfavoritar filme")
                            }
                            is ResultData.Loading -> {}
                        }
                    }
                }
            }
            is MovieDetailsEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    getMovieDetailsUseCase.invoke(
                            params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collect { resultData ->
                        when (resultData) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetails = resultData.data?.second,
                                    result = resultData.data?.first ?: emptyFlow()
                                )
                            }
                            is ResultData.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = resultData.e?.message.toString()
                                )
                                UtilFunctions.logError(
                                    "DETAIL-ERROR",
                                    resultData.e?.message.toString()
                                )
                            }
                            is ResultData.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
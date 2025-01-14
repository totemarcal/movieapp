package br.com.movieapp.movie_details_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_details_feature.domain.usercase.GetMovieDetailsUseCase
import br.com.movieapp.movie_details_feature.presentation.state.MovieDetailState
import br.com.movieapp.movie_popular_feature.presentation.state.MoviePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(MovieDetailState())
        private set

    fun getMovieDetail(getMovieDetail: MovieDetailsEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    private fun event(event: MovieDetailsEvent) {
        when (event) {
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
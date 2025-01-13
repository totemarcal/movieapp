package br.com.movieapp.movie_details_feature.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.common.ErrorScreen
import br.com.movieapp.core.presentation.components.common.LoadingView
import br.com.movieapp.movie_popular_feature.presentation.components.MovieItem

@Composable
fun MovieDetailsSimilarMovies(
    pagingMoviesSimilar: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        items(pagingMoviesSimilar.itemCount) { index ->
            val movie = pagingMoviesSimilar[index]
            movie?.let {
                MovieItem(
                    voteAverage = it.voteAverage,
                    imageUrl = it.imageUrl,
                    id = it.id,
                    onClick = {

                    }
                )
            }
        }
        pagingMoviesSimilar.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item (
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    )
                    {
                        LoadingView()
                    }
                }
                loadState.append is LoadState.Loading -> item (
                    span = {
                        GridItemSpan(maxLineSpan)
                    }
                )
                {
                    LoadingView()
                }
                loadState.refresh is LoadState.Error -> {
                    item (
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        ErrorScreen(
                            message = "Verifique sua conexão com a internet",
                            retry = {
                                retry()
                            }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    item (
                        span = {
                            GridItemSpan(maxLineSpan)
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(52.dp), // Ajuste o padding conforme necessário
                            contentAlignment = Alignment.TopCenter // Alinhar ao topo
                        ) {
                            ErrorScreen(
                                message = "Verifique sua conexão com a internet",
                                retry = {
                                    retry()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
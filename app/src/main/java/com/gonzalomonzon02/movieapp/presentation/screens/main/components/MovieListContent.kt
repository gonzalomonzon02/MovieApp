package com.gonzalomonzon02.movieapp.presentation.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.gonzalomonzon02.movieapp.R
import com.gonzalomonzon02.movieapp.domain.model.Movie
import com.gonzalomonzon02.movieapp.presentation.screens.components.LoadingVerticalItem

@Composable
fun MovieListContent(
    listState: LazyListState,
    movies: LazyPagingItems<Movie>,
    onMovieClicked: (movieId: Int) -> Unit
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies.itemCount) { index ->
            movies[index]?.let { movie ->
                MovieItem(
                    movie = movie,
                    onMovieClicked = onMovieClicked
                )
            }
        }

        when (movies.loadState.append) {
            is LoadState.Loading -> {
                item { LoadingVerticalItem() }
            }

            is LoadState.Error -> {
                item {
                    Text(
                        text = stringResource(R.string.error_loading),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            else -> Unit
        }
    }
}
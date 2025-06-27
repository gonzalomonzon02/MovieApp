package com.gonzalomonzon02.movieapp.presentation.screens.moviedetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gonzalomonzon02.movieapp.R
import com.gonzalomonzon02.movieapp.presentation.navigation.Destination
import com.gonzalomonzon02.movieapp.presentation.screens.components.EmptyView
import com.gonzalomonzon02.movieapp.presentation.screens.components.ErrorViewWithRetryButton
import com.gonzalomonzon02.movieapp.presentation.screens.components.LoadingView
import com.gonzalomonzon02.movieapp.presentation.screens.moviedetail.components.SuccessMovieDetail
import com.gonzalomonzon02.movieapp.presentation.theme.MovieAppTheme
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.routeMovieDetailScreen(navController: NavController) {
    composable<Destination.MovieDetailScreen> { backStackEntry ->
        val movieId = backStackEntry.toRoute<Destination.MovieDetailScreen>().movieId

        val movieDetailViewModel: MovieDetailViewModel = koinViewModel()
        val movieDetailUiState by movieDetailViewModel.movieDetailUiState.collectAsState()

        LaunchedEffect(movieId) {
            movieDetailViewModel.loadMovie(movieId)
        }

        MovieDetailScreen(
            movieDetailUiState = movieDetailUiState,
            onRetry = movieDetailViewModel::onRetry,
            onRelatedMovieClicked = { relatedMovieId ->
                navController.navigate(Destination.MovieDetailScreen(movieId = relatedMovieId))
            }
        )
    }
}

@Composable
private fun MovieDetailScreen(
    movieDetailUiState: MovieDetailUiState,
    onRetry: () -> Unit,
    onRelatedMovieClicked: (movieId: Int) -> Unit
) {
    when (movieDetailUiState) {
        is MovieDetailUiState.Loading -> LoadingView()

        is MovieDetailUiState.Error -> {
            val errorMessage = movieDetailUiState.message ?: stringResource(R.string.unknown_error)
            ErrorViewWithRetryButton(
                message = errorMessage,
                onRetry = onRetry
            )
        }

        is MovieDetailUiState.Empty -> EmptyView(
            onRefresh = onRetry,
            message = stringResource(R.string.movie_load_error)
        )

        is MovieDetailUiState.Success -> {
            val movie = movieDetailUiState.movie
            val relatedMovies = movieDetailUiState.relatedMovies
            SuccessMovieDetail(
                movie = movie,
                relatedMovies = relatedMovies,
                onRelatedMovieClicked = onRelatedMovieClicked
            )
        }
    }
}

@Preview(name = "MovieDetail States", showBackground = true)
@Composable
private fun MovieDetailScreenPreview(
    @PreviewParameter(MovieDetailUiStatePreviewProvider::class)
    state: MovieDetailUiState
) {
    MovieAppTheme {
        MovieDetailScreen(
            movieDetailUiState = state,
            onRetry = {},
            onRelatedMovieClicked = {}
        )
    }
}

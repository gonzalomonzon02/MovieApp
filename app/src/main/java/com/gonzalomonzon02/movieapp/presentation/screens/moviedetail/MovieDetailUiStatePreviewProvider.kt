package com.gonzalomonzon02.movieapp.presentation.screens.moviedetail

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.gonzalomonzon02.movieapp.presentation.preview.PreviewMovieSamples

class MovieDetailUiStatePreviewProvider : PreviewParameterProvider<MovieDetailUiState> {
    override val values = sequenceOf(
        MovieDetailUiState.Loading,
        MovieDetailUiState.Error("Error loading movie"),
        MovieDetailUiState.Empty,
        MovieDetailUiState.Success(
            movie = PreviewMovieSamples.movie,
            relatedMovies = PreviewMovieSamples.relatedMovies
        )
    )
}

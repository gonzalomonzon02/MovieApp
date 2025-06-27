package com.gonzalomonzon02.movieapp.presentation.screens.moviedetail

import com.gonzalomonzon02.movieapp.domain.model.Movie

sealed class MovieDetailUiState {
    data object Loading : MovieDetailUiState()
    data class Success(val movie: Movie, val relatedMovies: List<Movie>) : MovieDetailUiState()
    data class Error(val message: String?) : MovieDetailUiState()
    data object Empty : MovieDetailUiState()
}

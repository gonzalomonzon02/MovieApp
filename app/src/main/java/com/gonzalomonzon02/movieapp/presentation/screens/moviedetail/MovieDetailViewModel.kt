package com.gonzalomonzon02.movieapp.presentation.screens.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzalomonzon02.movieapp.domain.usecase.GetMovieDetailUseCase
import com.gonzalomonzon02.movieapp.domain.usecase.GetRelatedMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetail: GetMovieDetailUseCase,
    private val getRelatedMovies: GetRelatedMoviesUseCase
) : ViewModel() {
    private var movieId: Int = -1

    private val _movieDetailUiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Empty)
    val movieDetailUiState: StateFlow<MovieDetailUiState> = _movieDetailUiState

    fun loadMovie(movieId: Int) {
        this.movieId = movieId
        _movieDetailUiState.value = MovieDetailUiState.Loading
        viewModelScope.launch {
            try {
                val movie = getMovieDetail(movieId)
                val relatedMovies = getRelatedMovies(movieId)
                _movieDetailUiState.value = MovieDetailUiState.Success(
                    movie = movie, relatedMovies = relatedMovies
                )
            } catch (e: Exception) {
                _movieDetailUiState.value = MovieDetailUiState.Error(
                    message = e.message
                )
            }
        }
    }

    fun onRetry() {
        if (movieId == -1) return
        loadMovie(movieId = movieId)
    }
}

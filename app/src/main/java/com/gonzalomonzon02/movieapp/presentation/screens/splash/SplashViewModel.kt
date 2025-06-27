package com.gonzalomonzon02.movieapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonzalomonzon02.movieapp.domain.repository.Source
import com.gonzalomonzon02.movieapp.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val getPopularMovies: GetPopularMoviesUseCase) : ViewModel() {

    private val _navigateToNextScreen = MutableSharedFlow<Unit>()
    val navigateToNextScreen: SharedFlow<Unit> = _navigateToNextScreen

    init {
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            try {
                getPopularMovies(source = Source.Remote)
            } catch (_: Exception) {
            } finally {
                _navigateToNextScreen.emit(Unit)
            }
        }
    }
}

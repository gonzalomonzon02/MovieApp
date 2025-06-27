package com.gonzalomonzon02.movieapp.domain.usecase

import com.gonzalomonzon02.movieapp.domain.model.Movie
import com.gonzalomonzon02.movieapp.domain.repository.MovieRepository
import com.gonzalomonzon02.movieapp.domain.repository.Source
import com.gonzalomonzon02.movieapp.presentation.screens.main.components.SortOption

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(
        page: Int = 1,
        sortBy: SortOption? = null,
        genres: String? = null,
        query: String? = null,
        source: Source? = null
    ): List<Movie> {
        return movieRepository.getPopularMovies(page, sortBy, genres, query, source)
    }
} 
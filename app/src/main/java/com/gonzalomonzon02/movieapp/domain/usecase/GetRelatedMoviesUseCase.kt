package com.gonzalomonzon02.movieapp.domain.usecase

import com.gonzalomonzon02.movieapp.domain.model.Movie
import com.gonzalomonzon02.movieapp.domain.repository.MovieRepository

class GetRelatedMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: Int): List<Movie> {
        return emptyList()// movieRepository.getRelatedMovies(movieId)
    }
} 
package com.gonzalomonzon02.movieapp.domain.repository

import com.gonzalomonzon02.movieapp.domain.model.Movie
import com.gonzalomonzon02.movieapp.presentation.screens.main.components.SortOption

interface MovieRepository {
    suspend fun getPopularMovies(
        page: Int = 1,
        sortBy: SortOption? = null,
        genres: String? = null,
        query: String? = null,
        source: Source? = null
    ): List<Movie>

    suspend fun getMovieDetail(movieId: Int): Movie

    suspend fun getRelatedMovies(movieId: Int): List<Movie>
}

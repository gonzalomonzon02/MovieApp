package com.gonzalomonzon02.movieapp.data.repository

import com.gonzalomonzon02.movieapp.data.local.MovieDao
import com.gonzalomonzon02.movieapp.data.mapper.toDomain
import com.gonzalomonzon02.movieapp.data.mapper.toEntity
import com.gonzalomonzon02.movieapp.data.remote.MovieService
import com.gonzalomonzon02.movieapp.domain.model.Movie
import com.gonzalomonzon02.movieapp.domain.repository.MovieRepository
import com.gonzalomonzon02.movieapp.domain.repository.Source
import com.gonzalomonzon02.movieapp.presentation.screens.main.components.SortOption

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getPopularMovies(
        page: Int,
        sortBy: SortOption?,
        genres: String?,
        query: String?,
        source: Source?
    ): List<Movie> {
        return when (source) {
            is Source.Local -> {
                val allMovies = movieDao.getAllMovies().map { it.toDomain() }
                val pageSize = 20
                val startIndex = (page - 1) * pageSize
                val endIndex = startIndex + pageSize

                if (startIndex < allMovies.size) {
                    val paginatedMovies =
                        allMovies.subList(startIndex, minOf(endIndex, allMovies.size))
                    paginatedMovies
                } else {
                    emptyList()
                }
            }

            else -> {
                val movies = when {
                    query != null -> {
                        movieService.searchMovies(query = query, page = page)
                    }

                    sortBy != null -> {
                        movieService.discoverMovies(
                            page = page,
                            sortBy = sortBy.query ?: SortOption.getSortDefault(),
                            withGenres = genres
                        )
                    }

                    else -> {
                        movieService.getPopularMovies(page = page)
                    }
                }.results?.map { it.toDomain() }.orEmpty()

                if (page == 1) {
                    movieDao.clearMovies()
                    movieDao.insertMovies(movies.map { it.toEntity() })
                }

                movies
            }
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Movie {
        return movieService.getMovieDetail(movieId).toDomain()
    }

    override suspend fun getRelatedMovies(movieId: Int): List<Movie> {
        return movieService.getRelatedMovies(movieId)
            .results?.map { it.toDomain() }
            .orEmpty()
    }
}

package com.gonzalomonzon02.movieapp.utils

import com.gonzalomonzon02.movieapp.data.local.MovieEntity
import com.gonzalomonzon02.movieapp.data.mapper.toDomain
import com.gonzalomonzon02.movieapp.data.mapper.toEntity
import com.gonzalomonzon02.movieapp.data.model.MovieDto
import com.gonzalomonzon02.movieapp.domain.model.Movie

object TestMovieSamples {

    val movieDto = MovieDto(
        id = 1,
        title = "Matrix",
        overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel egestas dolor, nec dignissim metus.",
        posterPath = null,
        backdropPath = null,
        releaseDate = "1999-03-31",
        voteAverage = 8.7,
        genreIds = listOf(28, 878)
    )

    val movie: Movie = movieDto.toDomain()

    val movieEntity: MovieEntity = movie.toEntity()
}

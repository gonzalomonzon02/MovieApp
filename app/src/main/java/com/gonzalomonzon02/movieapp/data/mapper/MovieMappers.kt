package com.gonzalomonzon02.movieapp.data.mapper

import com.gonzalomonzon02.movieapp.data.local.MovieEntity
import com.gonzalomonzon02.movieapp.data.model.MovieDto
import com.gonzalomonzon02.movieapp.domain.model.Movie

fun MovieDto.toDomain(): Movie = Movie(
    id = id ?: -1,
    title = title.orEmpty(),
    overview = overview,
    posterUrl = posterPath,
    backdropUrl = backdropPath,
    releaseDate = releaseDate,
    rating = voteAverage,
    genres = genreIds
)

fun MovieEntity.toDomain(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    releaseDate = releaseDate,
    rating = rating,
    genres = genres?.split(",")?.mapNotNull { it.toIntOrNull() }
)

fun Movie.toEntity(): MovieEntity = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterUrl,
    backdropUrl = backdropUrl,
    releaseDate = releaseDate,
    rating = rating,
    genres = genres?.joinToString(",")
)

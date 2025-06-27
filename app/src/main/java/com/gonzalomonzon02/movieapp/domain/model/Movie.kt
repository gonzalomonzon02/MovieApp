package com.gonzalomonzon02.movieapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val releaseDate: String?,
    val rating: Double?,
    val genres: List<Int>?
) 
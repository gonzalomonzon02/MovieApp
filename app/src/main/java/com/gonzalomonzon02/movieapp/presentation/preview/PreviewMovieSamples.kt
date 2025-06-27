package com.gonzalomonzon02.movieapp.presentation.preview

import com.gonzalomonzon02.movieapp.domain.model.Movie

object PreviewMovieSamples {
    val movie: Movie = Movie(
        id = 1,
        title = "Matrix",
        overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vel egestas dolor, nec dignissim metus.",
        posterUrl = null,
        backdropUrl = null,
        releaseDate = "1999-03-31",
        rating = 8.7,
        genres = listOf(28, 878)
    )

    val relatedMovies: List<Movie> = listOf(
        movie.copy(id = 2, title = "Matrix Reloaded"),
        movie.copy(id = 3, title = "Matrix Revolutions")
    )
}

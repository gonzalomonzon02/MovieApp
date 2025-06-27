package com.gonzalomonzon02.movieapp.presentation.screens.moviedetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gonzalomonzon02.movieapp.domain.model.Movie

@Composable
fun SuccessMovieDetail(
    movie: Movie,
    relatedMovies: List<Movie>,
    onRelatedMovieClicked: (relatedMovieId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        MovieDetail(movie = movie)

        Spacer(modifier = Modifier.height(16.dp))

        RelatedMovies(
            relatedMovies = relatedMovies,
            onRelatedMovieClicked = onRelatedMovieClicked
        )
    }
}

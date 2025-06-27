package com.gonzalomonzon02.movieapp.presentation.screens.moviedetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gonzalomonzon02.movieapp.R
import com.gonzalomonzon02.movieapp.domain.model.Movie
import com.gonzalomonzon02.movieapp.presentation.screens.components.ImageURLView

@Composable
fun RelatedMovies(
    relatedMovies: List<Movie>,
    onRelatedMovieClicked: (movieId: Int) -> Unit
) = Column {
    Text(
        text = stringResource(R.string.related_movies),
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(relatedMovies) { relatedMovie ->
            RelatedMovieItem(
                relatedMovie = relatedMovie,
                onRelatedMovieClicked = onRelatedMovieClicked
            )
        }
    }
}


@Composable
private fun RelatedMovieItem(
    relatedMovie: Movie,
    onRelatedMovieClicked: (movieId: Int) -> Unit
) {
    val cornerRadius = 8.dp
    Card(
        modifier = Modifier
            .width(140.dp)
            .padding(end = 8.dp)
            .clickable { onRelatedMovieClicked(relatedMovie.id) },
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Column {
            ImageURLView(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topEnd = cornerRadius,
                            topStart = cornerRadius
                        )
                    ),
                url = relatedMovie.posterUrl.orEmpty(),
                contentDescription = relatedMovie.title
            )

            Text(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                ),
                text = relatedMovie.title,
                style = MaterialTheme.typography.bodyMedium,
                minLines = 2,
                maxLines = 2
            )
        }
    }
}
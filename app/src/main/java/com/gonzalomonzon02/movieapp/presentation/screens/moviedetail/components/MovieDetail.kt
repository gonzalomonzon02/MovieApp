package com.gonzalomonzon02.movieapp.presentation.screens.moviedetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gonzalomonzon02.movieapp.R
import com.gonzalomonzon02.movieapp.domain.model.Movie
import com.gonzalomonzon02.movieapp.presentation.screens.components.ImageURLView

@Composable
fun MovieDetail(movie: Movie) = Column {
    ImageURLView(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp)),
        url = movie.backdropUrl.orEmpty(),
        contentDescription = movie.title
    )

    Spacer(modifier = Modifier.height(12.dp))
    Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
    Text(text = movie.releaseDate ?: "", style = MaterialTheme.typography.bodySmall)
    Text(
        text = "★ ${movie.rating ?: "-"}",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(text = movie.overview ?: stringResource(R.string.no_description))
    Spacer(modifier = Modifier.height(16.dp))
}
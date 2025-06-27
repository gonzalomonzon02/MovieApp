package com.gonzalomonzon02.movieapp.presentation.screens.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gonzalomonzon02.movieapp.R

enum class SortOption(val query: String?) {
    NONE(null),
    POPULARITY("popularity.desc"),
    VOTE("vote_average.desc"),
    DATE("release_date.desc"),
    TITLE("title.asc");

    @Composable
    fun getLabel(): String {
        return when (this) {
            NONE -> stringResource(R.string.no_sort)
            POPULARITY -> stringResource(R.string.popularity)
            VOTE -> stringResource(R.string.rating)
            DATE -> stringResource(R.string.release_date)
            TITLE -> stringResource(R.string.title_az)
        }
    }

    companion object {
        @Composable
        fun getOptions(): List<Pair<SortOption, String>> {
            return entries.map { it to it.getLabel() }
        }

        fun getSortDefault(): String = POPULARITY.query.orEmpty()
    }
} 
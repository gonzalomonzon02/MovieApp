package com.gonzalomonzon02.movieapp.presentation.screens.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gonzalomonzon02.movieapp.domain.model.Movie
import com.gonzalomonzon02.movieapp.domain.repository.Source
import com.gonzalomonzon02.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.gonzalomonzon02.movieapp.presentation.screens.main.components.SortOption

class MoviesPagingSource(
    private val getPopularMovies: GetPopularMoviesUseCase,
    private val source: Source,
    private val sortBy: SortOption,
    private val query: String?
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val movies = getPopularMovies(
                page = page,
                source = source,
                sortBy = sortBy,
                query = query
            )
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)
                ?.prevKey
                ?.plus(1)
                ?: state.closestPageToPosition(position)
                    ?.nextKey
                    ?.minus(1)
        }
    }
}

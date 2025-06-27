package com.gonzalomonzon02.movieapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.gonzalomonzon02.movieapp.domain.repository.Source
import com.gonzalomonzon02.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.gonzalomonzon02.movieapp.presentation.screens.main.components.SortOption
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

class MainViewModel(
    private val getPopularMovies: GetPopularMoviesUseCase
) : ViewModel() {

    private val sortBy = MutableStateFlow(SortOption.POPULARITY)
    private val query = MutableStateFlow<String?>(null)
    private val source = MutableStateFlow<Source>(Source.Remote)

    @OptIn(ExperimentalCoroutinesApi::class)
    val moviesPager = combine(sortBy, query, source) { sort, query, src ->
        Triple(sort, query, src)
    }.flatMapLatest { (sort, query, src) ->
        Pager(PagingConfig(pageSize = 20)) {
            MoviesPagingSource(getPopularMovies, src, sort, query)
        }.flow
    }.cachedIn(viewModelScope)

    fun refreshData(sort: SortOption) {
        updateSort(sort)
        updateSearch(null)
        updateSource(Source.Remote)
    }

    fun updateSort(sort: SortOption) {
        sortBy.value = sort
    }

    fun updateSearch(query: String?) {
        this.query.value = query
    }

    private fun updateSource(source: Source) {
        this.source.value = source
    }
}

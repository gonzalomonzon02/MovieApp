package com.gonzalomonzon02.movieapp.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gonzalomonzon02.movieapp.R
import com.gonzalomonzon02.movieapp.domain.model.Movie
import com.gonzalomonzon02.movieapp.presentation.navigation.Destination
import com.gonzalomonzon02.movieapp.presentation.screens.components.EmptyView
import com.gonzalomonzon02.movieapp.presentation.screens.components.ErrorViewWithRetryButton
import com.gonzalomonzon02.movieapp.presentation.screens.components.LoadingView
import com.gonzalomonzon02.movieapp.presentation.screens.main.components.FiltersSection
import com.gonzalomonzon02.movieapp.presentation.screens.main.components.MovieListContent
import com.gonzalomonzon02.movieapp.presentation.screens.main.components.SortOption
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import org.koin.androidx.compose.koinViewModel

@OptIn(FlowPreview::class)
fun NavGraphBuilder.routeMainScreen(navController: NavController) {
    composable<Destination.MainScreen> {
        val viewModel: MainViewModel = koinViewModel()
        val moviesPager = viewModel.moviesPager.collectAsLazyPagingItems()
        val listState = rememberLazyListState()

        var searchQuery by rememberSaveable { mutableStateOf("") }
        var expandedSort by remember { mutableStateOf(false) }
        var selectedSortOption by remember { mutableStateOf(SortOption.POPULARITY) }

        LaunchedEffect(Unit) {
            snapshotFlow { searchQuery }
                .debounce(500)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collectLatest { query ->
                    viewModel.updateSearch(query)
                }
        }

        val loadState = moviesPager.loadState
        val isRefreshing = loadState.refresh is LoadState.Loading
        val isEmpty = loadState.refresh is LoadState.NotLoading && moviesPager.itemCount == 0
        val isError = loadState.refresh is LoadState.Error

        MainScreen(
            moviesPager = moviesPager,
            listState = listState,
            searchQuery = searchQuery,
            onSearchQueryChanged = { searchQuery = it },
            isRefreshing = isRefreshing,
            isError = isError,
            isEmpty = isEmpty,
            selectedSortOption = selectedSortOption,
            onSortSelected = {
                selectedSortOption = it
                viewModel.updateSort(it)
            },
            expandedSort = expandedSort,
            onSortExpandChange = { expandedSort = it },
            onRefreshFilter = {
                selectedSortOption = it
                searchQuery = ""
                viewModel.refreshData(sort = it)
            },
            onMovieClicked = { movieId ->
                navController.navigate(Destination.MovieDetailScreen(movieId = movieId))
            }
        )
    }
}

@Composable
private fun MainScreen(
    moviesPager: LazyPagingItems<Movie>,
    listState: LazyListState,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    isRefreshing: Boolean,
    isError: Boolean,
    isEmpty: Boolean,
    selectedSortOption: SortOption,
    onSortSelected: (SortOption) -> Unit,
    expandedSort: Boolean,
    onSortExpandChange: (Boolean) -> Unit,
    onRefreshFilter: (SortOption) -> Unit,
    onMovieClicked: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        FiltersSection(
            title = stringResource(R.string.movies),
            searchQuery = searchQuery,
            onSearchChange = onSearchQueryChanged,
            isRefreshing = isRefreshing,
            onRefresh = onRefreshFilter,
            sortExpanded = expandedSort,
            onSortExpandChange = onSortExpandChange,
            selectedSort = selectedSortOption,
            onSortSelected = onSortSelected
        )

        when {
            isRefreshing && moviesPager.itemCount == 0 -> LoadingView()

            isError -> ErrorViewWithRetryButton(
                message = stringResource(R.string.unknown_error),
                onRetry = moviesPager::retry
            )

            isEmpty -> EmptyView(
                onRefresh = moviesPager::refresh,
                message = stringResource(R.string.no_movies_found)
            )

            else -> MovieListContent(
                listState = listState,
                movies = moviesPager,
                onMovieClicked = onMovieClicked
            )
        }
    }
}


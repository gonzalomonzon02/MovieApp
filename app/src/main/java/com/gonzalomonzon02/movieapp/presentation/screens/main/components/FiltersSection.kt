package com.gonzalomonzon02.movieapp.presentation.screens.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gonzalomonzon02.movieapp.R
import com.gonzalomonzon02.movieapp.presentation.screens.components.DropdownSelector

@Composable
fun FiltersSection(
    title: String,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    isRefreshing: Boolean,
    onRefresh: (SortOption) -> Unit,
    sortExpanded: Boolean,
    onSortExpandChange: (Boolean) -> Unit,
    selectedSort: SortOption?,
    onSortSelected: (SortOption) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = {
                    onRefresh(SortOption.POPULARITY)
                }, enabled = !isRefreshing) {
                    if (isRefreshing) {
                        CircularProgressIndicator(Modifier.size(16.dp), strokeWidth = 2.dp)
                    } else {
                        Text(stringResource(R.string.refresh))
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                label = { Text(stringResource(R.string.search_movies)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(16.dp))

            DropdownSelector(
                label = selectedSort?.getLabel() ?: stringResource(R.string.sort_by),
                expanded = sortExpanded,
                modifier = Modifier.fillMaxWidth(),
                onExpandedChange = onSortExpandChange,
                options = SortOption.getOptions(),
                onOptionSelected = onSortSelected
            )
        }
    }
}
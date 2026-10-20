package com.example.library.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.library.ui.theme.LibraryTheme
import com.example.library.viewmodel.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(viewModel: LibraryViewModel = viewModel()) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Библиотека") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                SearchField(
                    query = viewModel.searchQuery,
                    onQueryChange = viewModel::onSearchChange
                )
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = viewModel.showOnlyAvailable,
                        onCheckedChange = viewModel::onFilterChange
                    )
                    Text("Только доступные")
                }
            }
            item { AddBookForm(onAdd = viewModel::addBook) }
            item { RotationDemo() }
            items(viewModel.filteredBooks, key = { it.id }) { book ->
                BookCard(
                    book = book,
                    onToggle = { viewModel.toggleBorrowed(book.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    LibraryTheme { LibraryScreen() }
}

@Preview(showBackground = true, device = "spec:width=891dp,height=411dp")
@Composable
fun LibraryScreenLandscapePreview() {
    LibraryTheme { LibraryScreen() }
}

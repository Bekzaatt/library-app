package com.example.library.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.library.model.Book

class LibraryViewModel : ViewModel() {

    private var nextId = 4

    val books = mutableStateListOf(
        Book(1, "1984", "George Orwell"),
        Book(2, "Мастер и Маргарита", "Михаил Булгаков", isBorrowed = true),
        Book(3, "Абай жолы", "Мухтар Ауэзов")
    )

    var searchQuery by mutableStateOf("")
        private set

    var showOnlyAvailable by mutableStateOf(false)
        private set

    val filteredBooks: List<Book>
        get() = books.filter { book ->
            val matchesSearch = book.title.contains(searchQuery, ignoreCase = true) ||
                    book.author.contains(searchQuery, ignoreCase = true)
            val matchesFilter = !showOnlyAvailable || !book.isBorrowed
            matchesSearch && matchesFilter
        }

    fun onSearchChange(query: String) {
        searchQuery = query
    }

    fun onFilterChange(value: Boolean) {
        showOnlyAvailable = value
    }

    fun addBook(title: String, author: String) {
        if (title.isBlank() || author.isBlank()) return
        books.add(Book(nextId++, title.trim(), author.trim()))
    }

    fun toggleBorrowed(id: Int) {
        val index = books.indexOfFirst { it.id == id }
        if (index >= 0) {
            books[index] = books[index].copy(isBorrowed = !books[index].isBorrowed)
        }
    }
}

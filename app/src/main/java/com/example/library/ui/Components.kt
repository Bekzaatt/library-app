package com.example.library.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.library.model.Book

@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Поиск по названию или автору") },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun BookCard(
    book: Book,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(book.title, style = MaterialTheme.typography.titleMedium)
                Text(book.author, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .background(
                            if (book.isBorrowed) MaterialTheme.colorScheme.errorContainer
                            else MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        if (book.isBorrowed) "Выдана" else "Доступна",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            Checkbox(checked = book.isBorrowed, onCheckedChange = { onToggle() })
        }
    }
}

@Composable
fun AddBookForm(
    onAdd: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Добавить книгу", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Название") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Автор") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                onAdd(title, author)
                title = ""
                author = ""
            },
            enabled = title.isNotBlank() && author.isNotBlank()
        ) {
            Text("Добавить")
        }
    }
}

@Composable
fun RotationDemo(modifier: Modifier = Modifier) {
    var rememberText by remember { mutableStateOf("") }
    var savedText by rememberSaveable { mutableStateOf("") }

    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Демо поворота экрана", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = rememberText,
                onValueChange = { rememberText = it },
                label = { Text("remember (сбросится при повороте)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = savedText,
                onValueChange = { savedText = it },
                label = { Text("rememberSaveable (сохранится)") },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "Список книг, поиск и фильтр хранятся во ViewModel и тоже сохранятся.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

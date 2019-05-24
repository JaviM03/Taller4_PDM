package com.example.library.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BookRepository
    val allWords: LiveData<List<Book>>

    init {
        val wordsDao = BookRoomDatabase.getDatabase(application).wordDao()
        repository = BookRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: Book) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}
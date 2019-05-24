package com.example.library.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AuthorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AuthorRepository
    val allAuthors: LiveData<List<Author>>

    init {
        val authorDao = AuthorRoomDatabase.getDatabase(application).authorDao()
        repository = AuthorRepository(AuthorDao)
        allAuthors = repository.allAuthors
    }

    fun insert(author: Author) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(author)
    }
}
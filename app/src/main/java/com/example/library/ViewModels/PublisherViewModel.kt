package com.example.library.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PublisherViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PublisherRepository
    val allPublishers: LiveData<List<Publisher>>

    init {
        val publishersDao = PublisherRoomDatabase.getDatabase(application).PublisherDao()
        repository = PublisherRepository(PublisherDao)
        allPublishers = repository.allPublishers
    }

    fun insert(publisher: Publisher) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(publisher)
    }
}
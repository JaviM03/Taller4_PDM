package com.example.library.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TagsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TagsRepository
    val allTags: LiveData<List<Tags>>

    init {
        val tagsDao = TagsRoomDatabase.getDatabase(application).TagsDao()
        repository = TagsRepository(TagsDao)
        allTags = repository.allTags
    }

    fun insert(tags: Tags) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(tags)
    }
}
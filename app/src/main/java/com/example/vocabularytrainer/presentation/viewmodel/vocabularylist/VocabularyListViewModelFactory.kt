package com.example.vocabularytrainer.presentation.viewmodel.vocabularylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vocabularytrainer.database.VocabularyDao
import java.lang.IllegalArgumentException

/**
 * ViewModel Factory for VocabularyListViewModel
 */
class VocabularyListViewModelFactory(
    private val databaseDao: VocabularyDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VocabularyListViewModel::class.java)) {
            return VocabularyListViewModel(
                databaseDao
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
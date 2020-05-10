package com.example.vocabularytrainer.presentation.viewmodel.wordcreator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vocabularytrainer.database.VocabularyDao
import java.lang.IllegalArgumentException

/**
 * ViewModel Factory for WordCreatorViewModel
 */
class WordCreatorViewModelFactory (
    private val databaseDao: VocabularyDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordCreatorViewModel::class.java)) {
            return WordCreatorViewModel(
                databaseDao
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
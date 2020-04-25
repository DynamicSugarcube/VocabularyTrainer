package com.example.vocabularytrainer.vocabularytrainer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vocabularytrainer.database.VocabularyDao
import java.lang.IllegalArgumentException

/**
 * ViewModel Factory for VocabularyTrainerViewModel
 */
class VocabularyTrainerViewModelFactory(
    private val databaseDao: VocabularyDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VocabularyTrainerViewModel::class.java)) {
            return VocabularyTrainerViewModel(databaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.vocabularytrainer.presentation.viewmodel.vocabularylist

import androidx.lifecycle.ViewModel
import com.example.vocabularytrainer.database.VocabularyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for VocabularyListFragment
 */
class VocabularyListViewModel(private val databaseDao: VocabularyDao): ViewModel() {

    /**
     * The value contains all words from the database
     */
    val vocabulary = databaseDao.getFullVocabulary()

    /**
     * Launch the coroutine to remove a word with the provided id
     * @param wordId is a word's id in the table
     */
    fun onRemoveWord(wordId: Long) {
        GlobalScope.launch {
            removeWordById(wordId)
        }
    }

    /**
     * Delete the word with provided id
     * It encapsulates the DAO removeWordById method in a suspend function
     * @param wordId is a word's id in the table
     */
    private suspend fun removeWordById(wordId: Long) {
        withContext(Dispatchers.IO) {
            databaseDao.removeWordById(wordId)
        }
    }
}
package com.example.vocabularytrainer.presentation.viewmodel.vocabularylist

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vocabularytrainer.database.VocabularyDao
import kotlinx.coroutines.*

/**
 * ViewModel for VocabularyListFragment
 */
class VocabularyListViewModel(private val databaseDao: VocabularyDao) : ViewModel() {

    private val tag = "VocabularyList"

    /**
     * The value contains all words from the database
     */
    val vocabulary = runBlocking {
        databaseDao.getFullVocabulary()
    }

    /**
     * Launch the coroutine to remove a word with the provided id
     * @param wordId is a word's id in the table
     */
    fun removeWord(wordId: Long) = GlobalScope.launch {
        Log.d(tag, "Removing word with $wordId")
        withContext(Dispatchers.IO) { databaseDao.removeWordById(wordId) }
    }
}
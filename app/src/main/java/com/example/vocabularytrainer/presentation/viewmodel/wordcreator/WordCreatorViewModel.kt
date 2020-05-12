package com.example.vocabularytrainer.presentation.viewmodel.wordcreator

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.vocabularytrainer.database.Lexeme
import com.example.vocabularytrainer.database.VocabularyDao
import kotlinx.coroutines.*

/**
 * ViewModel for WordCreatorFragment
 */
class WordCreatorViewModel(private val databaseDao: VocabularyDao): ViewModel() {

    private val tag = "WordCreator"

    /**
     * Insert a provided word into the database
     * @param lexeme is a word to insert
     */
    fun insert(lexeme: Lexeme) = GlobalScope.launch {
        Log.d(tag, "Inserting ${lexeme.word to lexeme.translation}")
        withContext(Dispatchers.IO) { databaseDao.insert(lexeme) }
    }
}
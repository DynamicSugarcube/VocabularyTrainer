package com.example.vocabularytrainer.wordcreator

import androidx.lifecycle.ViewModel
import com.example.vocabularytrainer.database.Lexeme
import com.example.vocabularytrainer.database.VocabularyDao
import kotlinx.coroutines.*

/**
 * ViewModel for WordCreatorFragment
 */
class WordCreatorViewModel(private val databaseDao: VocabularyDao): ViewModel() {

    /**
     * Insert a provided word into the database
     * @param lexeme is a word to insert
     */
    fun appendLexeme(lexeme: Lexeme) = runBlocking {
        val job = launch {
            databaseDao.insert(lexeme)
        }
        job.join()
    }
}
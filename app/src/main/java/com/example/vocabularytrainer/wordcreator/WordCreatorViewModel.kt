package com.example.vocabularytrainer.wordcreator

import androidx.lifecycle.ViewModel
import com.example.vocabularytrainer.database.Lexeme
import com.example.vocabularytrainer.database.VocabularyDao
import kotlinx.coroutines.*

class WordCreatorViewModel(private val databaseDao: VocabularyDao): ViewModel() {

    fun appendLexeme(lexeme: Lexeme) {
        GlobalScope.launch {
            insert(lexeme)
        }
    }

    private suspend fun insert(lexeme: Lexeme) {
        withContext(Dispatchers.IO) {
            databaseDao.insert(lexeme)
        }
    }
}
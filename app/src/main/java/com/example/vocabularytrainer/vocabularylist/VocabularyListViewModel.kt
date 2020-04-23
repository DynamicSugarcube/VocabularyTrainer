package com.example.vocabularytrainer.vocabularylist

import androidx.lifecycle.ViewModel
import com.example.vocabularytrainer.database.VocabularyDao

class VocabularyListViewModel(private val databaseDao: VocabularyDao): ViewModel() {

    val vocabulary = databaseDao.getFullVocabulary()
}
package com.example.vocabularytrainer

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vocabularytrainer.database.Lexeme
import com.example.vocabularytrainer.database.VocabularyDao
import com.example.vocabularytrainer.database.VocabularyDatabase
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class VocabularyDatabaseTest {

    private lateinit var vocabularyDao: VocabularyDao
    private lateinit var vocabularyDatabase: VocabularyDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        vocabularyDatabase = Room.inMemoryDatabaseBuilder(context, VocabularyDatabase::class.java)
            .allowMainThreadQueries().build()

        vocabularyDao = vocabularyDatabase.vocabularyDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        vocabularyDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWord() {
        val lexeme = Lexeme(word = "word", translation = "translation")
        lexeme.wordId = vocabularyDao.insert(lexeme)

        val word = vocabularyDao.getWordById(lexeme.wordId)
        assertEquals(lexeme, word)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetFullVocabulary() {
        val vocabulary = listOf(
            Lexeme(word = "w1", translation = "t1"),
            Lexeme(word = "w2", translation = "t2"),
            Lexeme(word = "w3", translation = "t3"))
        for (lexeme in vocabulary) {
            lexeme.wordId = vocabularyDao.insert(lexeme)
        }

        val words = vocabularyDao.getFullVocabulary()
        assertEquals(vocabulary.reversed(), words)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndClean() {
        val vocabulary = listOf(
            Lexeme(word = "w1", translation = "t1"),
            Lexeme(word = "w2", translation = "t2"),
            Lexeme(word = "w3", translation = "t3"))
        for (lexeme in vocabulary) {
            lexeme.wordId = vocabularyDao.insert(lexeme)
        }

        vocabularyDao.clean()
        val words = vocabularyDao.getFullVocabulary()
        assertEquals(listOf<Lexeme>(), words)
    }
}
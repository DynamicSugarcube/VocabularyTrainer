package com.example.vocabularytrainer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO defining methods for using Lexeme class with VocabularyDatabase
 */
@Dao
interface VocabularyDao {

    /**
     * Insert a provided word into the database
     * @param lexeme is a word to insert
     * @return the primary key of this word
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lexeme: Lexeme): Long

    /**
     * Fetch a word with the provided id
     * @param key is a primary key (a word's id in the table)
     * @return the word with that id
     */
    @Query("SELECT * FROM vocabulary_table WHERE wordId = :key")
    fun getWordById(key: Long): Lexeme?

    /**
     * Fetch a random word from the table
     * @return a random word
     */
    @Query("SELECT * FROM vocabulary_table ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWord(): Lexeme?

    /**
     * Fetch all words from the table
     * @return a list of all words
     */
    @Query("SELECT * FROM vocabulary_table ORDER BY wordId DESC")
    fun getFullVocabulary(): LiveData<List<Lexeme>>

    /**
     * Delete the word with provided id
     * @param key is a primary key (a word's id in the table)
     */
    @Query("DELETE FROM vocabulary_table WHERE wordId = :key")
    fun removeWordById(key: Long)

    /**
     * Delete all words in the table
     */
    @Query("DELETE FROM vocabulary_table")
    fun clean()
}
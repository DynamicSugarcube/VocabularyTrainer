package com.example.vocabularytrainer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO defining methods for using Lexeme class with VocabularyDatabase
 */
@Dao
interface VocabularyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lexeme: Lexeme): Long

    @Query("SELECT * FROM vocabulary_table WHERE wordId = :key")
    fun getWordById(key: Long): Lexeme?

    @Query("SELECT * FROM vocabulary_table ORDER BY wordId DESC")
    fun getFullVocabulary(): List<Lexeme>?

    @Query("DELETE FROM vocabulary_table")
    fun clean()
}
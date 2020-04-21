package com.example.vocabularytrainer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocabulary_table")
data class Lexeme(
    @PrimaryKey(autoGenerate = true)
    var wordId: Long = 0L,

    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "translation")
    val translation: String
)
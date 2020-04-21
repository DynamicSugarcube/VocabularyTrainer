package com.example.vocabularytrainer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * A database that stores all added vocabulary
 */
@Database(entities = [Lexeme::class], version = 1, exportSchema = false)
abstract class VocabularyDatabase: RoomDatabase() {

    /**
     * Connects the database to the DAO
     */
    abstract val vocabularyDao: VocabularyDao

    companion object {
        @Volatile
        private var INSTANCE: VocabularyDatabase? = null

        fun getInstance(context: Context): VocabularyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        VocabularyDatabase::class.java, "vocabulary_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
package com.example.vocabularytrainer.vocabularytrainer

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.vocabularytrainer.database.Lexeme
import com.example.vocabularytrainer.database.VocabularyDao
import com.example.vocabularytrainer.database.VocabularyDatabase
import kotlinx.coroutines.*

/**
 * ViewModel for VocabularyTrainer
 */
class VocabularyTrainerViewModel(private val databaseDao: VocabularyDao): ViewModel() {

    private val TAG = "VocabularyTrainer"

    /**
     * The value contains the current word to translate
     */
    private val _currentWord = MutableLiveData<Lexeme?>()
    val currentWord: LiveData<Lexeme?>
        get() = _currentWord

    init {
        _currentWord.value = getRandomWord()
    }

    companion object {
        /**
         * Create VocabularyTrainerViewModel
         * @return a new instance of VocabularyTrainerViewModel
         */
        fun createViewModel(fragment: Fragment): VocabularyTrainerViewModel {
            val application = requireNotNull(fragment.activity).application
            val databaseDao = VocabularyDatabase.getInstance(application).vocabularyDao
            val viewModelFactory = VocabularyTrainerViewModelFactory(databaseDao)
            return ViewModelProvider(fragment, viewModelFactory)
                .get(VocabularyTrainerViewModel::class.java)
        }
    }

    /**
     * Check if the provided translation is correct
     * Move to the next word if correct
     * @return true if correct, false otherwise
     */
    fun checkTranslation(translation: String): Boolean {
        if (translation == currentWord.value?.translation) {
            updateWord()
            return true
        }
        return false
    }

    /**
     * Update the current word to the next one
     */
    private fun updateWord() {
        _currentWord.value = getRandomWord()
    }

    /**
     * Fetch a random word from the database
     * @return a random word from the table
     */
    private fun getRandomWord(): Lexeme? {
        return runBlocking {
            val lexeme = databaseDao.getRandomWord()
            Log.d(TAG, "Next word is $lexeme")
            lexeme
        }
    }
}
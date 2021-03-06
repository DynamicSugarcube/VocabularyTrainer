package com.example.vocabularytrainer.presentation.viewmodel.vocabularytrainer

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

    private val tag = "VocabularyTrainer"

    /**
     * The value contains the current word to translate
     */
    private val _currentWord = MutableLiveData<Lexeme?>()
    val currentWord: LiveData<Lexeme?>
        get() = _currentWord

    init {
        updateWord()
    }

    companion object {
        /**
         * Create VocabularyTrainerViewModel
         * @return a new instance of VocabularyTrainerViewModel
         */
        fun createViewModel(fragment: Fragment): VocabularyTrainerViewModel {
            val application = requireNotNull(fragment.activity).application
            val databaseDao = VocabularyDatabase.getInstance(application).vocabularyDao
            val viewModelFactory =
                VocabularyTrainerViewModelFactory(
                    databaseDao
                )
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
    private fun updateWord() = runBlocking {
        _currentWord.value = withContext(Dispatchers.IO) { databaseDao.getRandomWord() }
        Log.d(tag, "Next word is ${currentWord.value.toString()}")
    }
}
package com.example.vocabularytrainer.wordcreator

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.vocabularytrainer.R
import com.example.vocabularytrainer.database.Lexeme
import com.example.vocabularytrainer.database.VocabularyDatabase
import kotlinx.android.synthetic.main.fragment_word_creator.view.*

/**
 * Fragment that displays UI which allows users to create their own words
 */
class WordCreatorFragment : Fragment() {

    private lateinit var wordEditText: EditText
    private lateinit var translationEditText: EditText
    private lateinit var submitButton: Button

    private lateinit var viewModel: WordCreatorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_word_creator, container, false)

        val application = requireNotNull(this.activity).application
        val databaseDao = VocabularyDatabase.getInstance(application).vocabularyDao

        val viewModelFactory = WordCreatorViewModelFactory(databaseDao)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(WordCreatorViewModel::class.java)

        wordEditText = view.enter_word_text
        translationEditText = view.enter_translation_text

        submitButton = view.submit_button
        submitButton.setOnClickListener {
            createNewWord()
        }

        return view
    }

    /**
     * Check if the entered word is not null
     * @return true if editText texts are not empty, false otherwise
     */
    private fun checkWordNotNull(): Boolean {
        if (wordEditText.text.isNotEmpty() && translationEditText.text.isNotEmpty()) {
            return true
        }
        return false
    }

    /**
     * Clear EditText texts and focus on wordEditText
     */
    private fun resetFields() {
        wordEditText.text.clear()
        translationEditText.text.clear()

        wordEditText.requestFocus()
    }

    /**
     * Create and insert a word into the database if it is possible.
     * Otherwise, make a toast notifying that there is nothing to submit
     */
    private fun createNewWord() {
        val toast = Toast.makeText(context, R.string.nothing_submit_toast, Toast.LENGTH_LONG)

        if (checkWordNotNull()) {
            viewModel.appendLexeme(
                Lexeme(
                    word = wordEditText.text.toString(),
                    translation = translationEditText.text.toString()
                )
            )
            toast.setText(R.string.word_created_toast)
        }

        with(toast) {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }

        resetFields()
    }
}

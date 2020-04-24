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
import androidx.navigation.findNavController

import com.example.vocabularytrainer.R
import com.example.vocabularytrainer.database.Lexeme
import com.example.vocabularytrainer.database.VocabularyDatabase
import kotlinx.android.synthetic.main.fragment_word_creator.view.*

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

    private fun createNewWord() {
        viewModel.appendLexeme(Lexeme(word = wordEditText.text.toString(),
            translation = translationEditText.text.toString()))

        with (Toast.makeText(context, R.string.word_created_string, Toast.LENGTH_LONG)) {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }

        wordEditText.text.clear()
        translationEditText.text.clear()

        wordEditText.requestFocus()
    }
}

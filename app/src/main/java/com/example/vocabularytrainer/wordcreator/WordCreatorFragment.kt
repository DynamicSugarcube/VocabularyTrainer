package com.example.vocabularytrainer.wordcreator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

import com.example.vocabularytrainer.R
import com.example.vocabularytrainer.database.Lexeme
import com.example.vocabularytrainer.vocabularylist.colors
import kotlinx.android.synthetic.main.fragment_word_creator.view.*

class WordCreatorFragment : Fragment() {

    private lateinit var wordEditText: EditText
    private lateinit var translationEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_word_creator, container, false)

        wordEditText = view.enter_word_text
        translationEditText = view.enter_translation_text

        submitButton = view.submit_button
        submitButton.setOnClickListener {
            createLexeme(wordEditText.text.toString(),
                translationEditText.text.toString())

            it.findNavController()
                .navigate(R.id.action_wordCreatorFragment_to_vocabularyListFragment)
        }

        return view
    }

    private fun createLexeme(word: String, translation: String) {
        colors.add(Lexeme(word = word, translation = translation))
    }

}

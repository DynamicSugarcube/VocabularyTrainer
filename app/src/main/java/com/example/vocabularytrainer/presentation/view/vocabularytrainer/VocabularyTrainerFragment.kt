package com.example.vocabularytrainer.presentation.view.vocabularytrainer

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.example.vocabularytrainer.R
import com.example.vocabularytrainer.presentation.viewmodel.vocabularytrainer.VocabularyTrainerViewModel
import kotlinx.android.synthetic.main.fragment_vocabulary_trainer.view.*

/**
 * Fragment that displays UI with a translation game.
 * It shows the word, and a user should enter its translation
 */
class VocabularyTrainerFragment : Fragment() {

    private lateinit var wordTextView: TextView
    private lateinit var translationEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var viewWordsButton: Button

    private lateinit var viewModel: VocabularyTrainerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vocabulary_trainer, container, false)

        viewModel =
            VocabularyTrainerViewModel.createViewModel(
                this
            )

        wordTextView = view.word_text
        translationEditText = view.enter_translation_text

        checkButton = view.check_button
        checkButton.setOnClickListener {
            with(Toast.makeText(context, R.string.incorrect_toast, Toast.LENGTH_SHORT)) {
                if (viewModel.checkTranslation(translationEditText.text.toString())) {
                    setText(R.string.correct_toast)
                }
                setGravity(Gravity.CENTER, 0, 0)
                show()
            }
            translationEditText.text.clear()
        }

        viewWordsButton = view.view_words_button
        viewWordsButton.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_vocabularyTrainerFragment_to_vocabularyListFragment)
        }

        viewModel.currentWord.observe(viewLifecycleOwner, Observer {
            wordTextView.text = it?.word
        })

        return view
    }
}

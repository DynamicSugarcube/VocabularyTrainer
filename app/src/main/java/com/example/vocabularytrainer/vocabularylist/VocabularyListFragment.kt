package com.example.vocabularytrainer.vocabularylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.vocabularytrainer.R
import com.example.vocabularytrainer.database.Lexeme
import kotlinx.android.synthetic.main.fragment_vocabulary_list.view.*

/**
 * A vocabulary list for testing
 */
val colors = mutableListOf(
    Lexeme(word = "red", translation = "красный"),
    Lexeme(word = "orange", translation = "оранжевый"),
    Lexeme(word = "yellow", translation = "жёлтый"),
    Lexeme(word = "green", translation = "зелёный"),
    Lexeme(word = "blue", translation = "синий"),
    Lexeme(word = "indigo", translation = "индиго"),
    Lexeme(word = "violet", translation = "фиолетовый"),
    Lexeme(word = "white", translation = "белый"),
    Lexeme(word = "grey", translation = "серый"),
    Lexeme(word = "black", translation = "чёрный")
)

class VocabularyListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var addNewWordButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vocabulary_list, container, false)

        viewManager = LinearLayoutManager(this.context)
        viewAdapter = VocabularyListAdapter(colors)

        recyclerView = view.vocabulary_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        addNewWordButton = view.add_new_word_button
        addNewWordButton.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_vocabularyListFragment_to_wordCreatorFragment)
        }

        return view
    }
}

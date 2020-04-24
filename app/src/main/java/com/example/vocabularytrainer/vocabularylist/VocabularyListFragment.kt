package com.example.vocabularytrainer.vocabularylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.vocabularytrainer.R
import com.example.vocabularytrainer.database.VocabularyDatabase
import kotlinx.android.synthetic.main.fragment_vocabulary_list.view.*

class VocabularyListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var addNewWordButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vocabulary_list, container, false)

        val application = requireNotNull(this.activity).application
        val databaseDao = VocabularyDatabase.getInstance(application).vocabularyDao

        val viewModelFactory = VocabularyListViewModelFactory(databaseDao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(VocabularyListViewModel::class.java)


        viewManager = LinearLayoutManager(this.context)
        viewAdapter = VocabularyListAdapter(viewModel)

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

        viewModel.vocabulary.observe(this.viewLifecycleOwner, Observer {
            (viewAdapter as VocabularyListAdapter).data = it
        })

        return view
    }
}

package com.example.vocabularytrainer.vocabularylist

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vocabularytrainer.R
import com.example.vocabularytrainer.database.Lexeme
import kotlinx.android.synthetic.main.list_item_lexeme.view.*

class VocabularyListAdapter(private val viewModel: VocabularyListViewModel):
    RecyclerView.Adapter<VocabularyListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View,
                     private val viewModel: VocabularyListViewModel):
        RecyclerView.ViewHolder(itemView) {

        private val wordText = itemView.word_text
        private val translationText = itemView.translation_text

        fun bind(lexeme: Lexeme) {
            wordText.text = lexeme.word
            translationText.text = lexeme.translation

            itemView.setOnLongClickListener {
                showDeleteWordDialog(lexeme.wordId, itemView.context)
                true
            }
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: VocabularyListViewModel): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_lexeme, parent, false)
                return ViewHolder(view, viewModel)
            }
        }

        private fun showDeleteWordDialog(wordId: Long, context: Context) {
            val builder = AlertDialog.Builder(context)

            with(builder) {
                setMessage(R.string.dialog_delete_word_string)
                setPositiveButton(R.string.delete_string) { dialog, which ->
                    viewModel.onRemoveWord(wordId)
                }
                setNegativeButton(R.string.cancel_string) { dialog, which ->
                    // do nothing
                }
                show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    var data = listOf<Lexeme>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
}
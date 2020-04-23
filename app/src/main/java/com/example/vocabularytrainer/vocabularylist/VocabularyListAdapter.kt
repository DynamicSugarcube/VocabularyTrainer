package com.example.vocabularytrainer.vocabularylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vocabularytrainer.R
import com.example.vocabularytrainer.database.Lexeme
import kotlinx.android.synthetic.main.list_item_lexeme.view.*

class VocabularyListAdapter():
    RecyclerView.Adapter<VocabularyListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val wordText = itemView.word_text
        private val translationText = itemView.translation_text

        fun bind(lexeme: Lexeme) {
            wordText.text = lexeme.word
            translationText.text = lexeme.translation
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_lexeme, parent, false)
                return ViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
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
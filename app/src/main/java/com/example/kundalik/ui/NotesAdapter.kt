package com.example.kundalik.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kundalik.databinding.ItemNotesBinding
import com.example.kundalik.domain.Notes

class NotesAdapter(private val noteList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(notes: Notes)
    }

    private lateinit var onItemClickListener: OnItemClickListener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    class NotesViewHolder(private val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: Notes) {
            binding.apply {
                with(binding) {
                    textViewTitle.text = notes.title
                    textViewDescription.text = notes.description
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notes = noteList[position]
        holder.bind(notes)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(noteList[holder.adapterPosition])
        }
    }

}
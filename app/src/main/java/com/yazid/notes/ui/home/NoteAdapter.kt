package com.yazid.notes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yazid.notes.databinding.ItemNoteBinding
import com.yazid.notes.model.note.Note

class NoteAdapter(private val onItemClick: (Note) -> Unit): ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context))
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
        holder.itemView.setOnClickListener { onItemClick(note) }
    }

    class NoteViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvContent.text = note.content
            binding.tvDate.text = note.createdAt.toString()
        }
    }

    private class NoteDiffCallBack : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem
    }
}
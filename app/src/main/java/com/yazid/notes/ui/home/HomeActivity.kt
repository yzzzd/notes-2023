package com.yazid.notes.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.yazid.notes.databinding.ActivityHomeBinding
import com.yazid.notes.ui.add.NoteAddActivity
import com.yazid.notes.ui.detail.NoteDetailActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteAdapter = NoteAdapter {
            val detailIntent = Intent(this, NoteDetailActivity::class.java).apply {
                putExtra(NoteDetailActivity.TITLE, it.title)
                putExtra(NoteDetailActivity.CONTENT, it.content)
                putExtra(NoteDetailActivity.CREATED_AT, it.createdAt)
            }
            startActivity(detailIntent)
        }
        binding.rvNote.adapter = noteAdapter

        binding.btnAdd.setOnClickListener {
            val addNoteIntent = Intent(this, NoteAddActivity::class.java)
            startActivity(addNoteIntent)
        }

        observeData()

        viewModel.getNote()
    }

    private fun observeData() {
        viewModel.notes.observe(this) { notes ->
            noteAdapter.submitList(notes)
            binding.viewEmpty.isVisible = notes.isEmpty()
        }
    }
}
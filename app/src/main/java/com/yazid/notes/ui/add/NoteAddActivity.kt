package com.yazid.notes.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yazid.notes.R
import com.yazid.notes.databinding.ActivityNoteAddBinding
import com.yazid.notes.model.note.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteAddBinding

    private val viewModel: NoteAddViewModel by viewModels()

    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        id = intent.getIntExtra(ID, 0)

        observe()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getNote(id)
    }

    private fun observe() {
        viewModel.note.observe(this) {
            binding.etTitle.setText(it.title)
            binding.etContent.setText(it.content)
            binding.tvDate.text = it.createdAt.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_save -> {
                val inputTitle = binding.etTitle.text.toString().trim()
                val inputContent = binding.etContent.text.toString().trim()
                val note = Note(
                    id = id,
                    title = inputTitle,
                    content = inputContent,
                    createdAt = System.currentTimeMillis()
                )
                if (id == 0) {
                    viewModel.addNote(note)
                } else {
                    viewModel.updateNote(note)
                }
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val CONTENT = "content"
        const val CREATED_AT = "createdAt"
    }
}
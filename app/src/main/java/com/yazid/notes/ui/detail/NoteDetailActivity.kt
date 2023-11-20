package com.yazid.notes.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yazid.notes.R
import com.yazid.notes.databinding.ActivityNoteDetailBinding
import com.yazid.notes.ui.add.NoteAddActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding

    private val viewModel: NoteDetailViewModel by viewModels()

    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
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
            binding.tvTitle.text = it.title
            binding.tvContent.text = it.content
            binding.tvDate.text = it.createdAt.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_edit -> {
                val addIntent = Intent(this, NoteAddActivity::class.java).apply {
                    putExtra(NoteAddActivity.ID, id)
                }
                startActivity(addIntent)
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
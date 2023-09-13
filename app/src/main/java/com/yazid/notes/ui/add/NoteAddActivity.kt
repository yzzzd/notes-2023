package com.yazid.notes.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.yazid.notes.R
import com.yazid.notes.databinding.ActivityNoteAddBinding
import com.yazid.notes.ui.detail.NoteDetailActivity

class NoteAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteAddBinding

    private var title: String? = ""
    private var content: String? = ""
    private var createdAt: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        title = intent.getStringExtra(NoteDetailActivity.TITLE)
        content = intent.getStringExtra(NoteDetailActivity.CONTENT)
        createdAt = intent.getStringExtra(NoteDetailActivity.CREATED_AT)

        binding.etTitle.setText(title ?: "")
        binding.etContent.setText(content ?: "")
        binding.tvDate.text = createdAt ?: ""
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_save -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val TITLE = "title"
        const val CONTENT = "content"
        const val CREATED_AT = "createdAt"
    }
}
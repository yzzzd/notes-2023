package com.yazid.notes.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.yazid.notes.R
import com.yazid.notes.databinding.ActivityNoteDetailBinding
import com.yazid.notes.ui.add.NoteAddActivity

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding

    private var title: String? = ""
    private var content: String? = ""
    private var createdAt: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        title = intent.getStringExtra(TITLE)
        content = intent.getStringExtra(CONTENT)
        createdAt = intent.getStringExtra(CREATED_AT)

        binding.tvTitle.text = title
        binding.tvContent.text = content
        binding.tvDate.text = createdAt
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_edit -> {
                val addIntent = Intent(this, NoteAddActivity::class.java).apply {
                    putExtra(NoteAddActivity.TITLE, title)
                    putExtra(NoteAddActivity.CONTENT, content)
                    putExtra(NoteAddActivity.CREATED_AT, createdAt)
                }
                startActivity(addIntent)
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
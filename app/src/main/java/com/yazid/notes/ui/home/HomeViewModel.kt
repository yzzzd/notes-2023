package com.yazid.notes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazid.notes.model.note.Note
import com.yazid.notes.model.note.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val noteDao: NoteDao): ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun getNote() {
        val notesData = listOf(
            Note(0, "The standard Lorem Ipsum passage", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", System.currentTimeMillis()),
            Note(0, "Ut enim ad minima veniam", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", System.currentTimeMillis()),
            Note(0, "Nemo enim ipsam voluptatem", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", System.currentTimeMillis())
        )

        // _notes.value = notesData

        viewModelScope.launch {
            _notes.value = noteDao.getAllNote()
        }
    }
}
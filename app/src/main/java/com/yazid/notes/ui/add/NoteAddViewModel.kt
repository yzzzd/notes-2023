package com.yazid.notes.ui.add

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
class NoteAddViewModel @Inject constructor(private val noteDao: NoteDao): ViewModel() {
    fun addNote(note: Note) {
        viewModelScope.launch {
            noteDao.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteDao.updateNote(note)
        }
    }

    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> = _note

    fun getNote(id: Int) {
        viewModelScope.launch {
            _note.value = noteDao.getNoteById(id)
        }
    }
}
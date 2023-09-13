package com.yazid.notes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yazid.notes.model.Note

class HomeViewModel: ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun getNote() {
        val notesData = listOf(
            Note("The standard Lorem Ipsum passage", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "2022-12-12"),
            Note("Ut enim ad minima veniam", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "2022-12-12"),
            Note("Nemo enim ipsam voluptatem", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "2022-12-12")
        )

        _notes.value = notesData
    }
}
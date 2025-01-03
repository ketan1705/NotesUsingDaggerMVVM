package com.ken.notesusingdaggermvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ken.notesusingdaggermvvm.model.Notes
import com.ken.notesusingdaggermvvm.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(val repository: NotesRepository) : ViewModel() {

    private val _notes = MutableLiveData<Notes>()
    val notes: LiveData<Notes> get() = _notes
    val allNotes: LiveData<List<Notes>> get() = repository.getAllNotes()


    fun insertNotes(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertNote(notes)
            } catch (e: Exception) {
                Log.e("NotesViewModel", "Exception: $e")
            }
        }
    }

    fun getNotesById(id: Int) {
        viewModelScope.launch {
            val notes = repository.getNotesById(id)
            _notes.postValue(notes)
        }
    }

    fun getAllNotes() {
        viewModelScope.launch {
            repository.getAllNotes()
        }
    }

    fun deleteNotes(id: Int) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }
}
package com.ken.notesusingdaggermvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class NotesViewModelFactory @Inject constructor(private val notesViewModel: NotesViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return notesViewModel as T
    }
}
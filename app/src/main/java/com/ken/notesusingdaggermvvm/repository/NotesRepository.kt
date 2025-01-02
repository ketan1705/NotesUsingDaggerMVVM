package com.ken.notesusingdaggermvvm.repository

import androidx.lifecycle.LiveData
import com.ken.notesusingdaggermvvm.db.NotesDao
import com.ken.notesusingdaggermvvm.model.Notes
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesDao: NotesDao,
) {
    suspend fun insertNote(note: Notes) = notesDao.insertNote(note)
    suspend fun getNotesById(id: Int): Notes {
        return notesDao.getNoteById(id)
    }

    fun getAllNotes(): LiveData<List<Notes>> {
        return notesDao.getAllNotes()
    }

    suspend fun deleteNote(id: Int) = notesDao.deleteNoteById(id)
}
package com.ken.notesusingdaggermvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ken.notesusingdaggermvvm.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao
}
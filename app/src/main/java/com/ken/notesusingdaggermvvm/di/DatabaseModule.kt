package com.ken.notesusingdaggermvvm.di

import android.content.Context
import androidx.room.Room
import com.ken.notesusingdaggermvvm.db.NotesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "notes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(notesDatabase: NotesDatabase) = notesDatabase.notesDao()
}
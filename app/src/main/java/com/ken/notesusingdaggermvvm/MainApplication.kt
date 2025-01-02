package com.ken.notesusingdaggermvvm

import android.app.Application
import com.ken.notesusingdaggermvvm.di.DaggerNotesComponent
import com.ken.notesusingdaggermvvm.di.NotesComponent

class MainApplication : Application() {
    lateinit var notesComponent: NotesComponent
    override fun onCreate() {
        super.onCreate()

        notesComponent = DaggerNotesComponent.factory().create(this)

    }
}
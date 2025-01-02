package com.ken.notesusingdaggermvvm.di

import android.content.Context
import com.ken.notesusingdaggermvvm.activity.AddNoteActivity
import com.ken.notesusingdaggermvvm.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface NotesComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: AddNoteActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): NotesComponent
    }
}
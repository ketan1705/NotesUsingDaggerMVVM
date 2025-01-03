package com.ken.notesusingdaggermvvm.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ken.notesusingdaggermvvm.MainApplication
import com.ken.notesusingdaggermvvm.R
import com.ken.notesusingdaggermvvm.adapter.NotesAdapter
import com.ken.notesusingdaggermvvm.databinding.ActivityMainBinding
import com.ken.notesusingdaggermvvm.viewmodel.NotesViewModel
import com.ken.notesusingdaggermvvm.viewmodel.NotesViewModelFactory
import jakarta.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var notesViewModel: NotesViewModel

    @Inject
    lateinit var notesViewModelFactory: NotesViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        (application as MainApplication).notesComponent.inject(this)

        notesViewModel = ViewModelProvider(
            this,
            notesViewModelFactory
        )[NotesViewModel::class.java]

//        val dateTime = getCurrentDateTime()
//        notesViewModel.insertNotes(Notes(1, "Title 2", "Description 1", dateTime))
        notesViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let {
                binding.notesAdapter = NotesAdapter(this, it) { id ->
                    notesViewModel.deleteNotes(id)
                }
                it.forEach { it ->
                    Log.d("MainActivity", "All Notes: ${it.title}")
                }
            }
        })

        binding.addNotes.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }


}

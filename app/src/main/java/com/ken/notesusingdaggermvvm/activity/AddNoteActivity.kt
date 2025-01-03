package com.ken.notesusingdaggermvvm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ken.notesusingdaggermvvm.MainApplication
import com.ken.notesusingdaggermvvm.R
import com.ken.notesusingdaggermvvm.databinding.ActivityAddNoteBinding
import com.ken.notesusingdaggermvvm.model.Notes
import com.ken.notesusingdaggermvvm.utils.Constants.getCurrentDateTime
import com.ken.notesusingdaggermvvm.viewmodel.NotesViewModel
import com.ken.notesusingdaggermvvm.viewmodel.NotesViewModelFactory
import javax.inject.Inject

class AddNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddNoteBinding

    @Inject
    lateinit var factory: NotesViewModelFactory
    lateinit var viewModel: NotesViewModel
    val noteId: Int
        get() = intent.getIntExtra("noteID", -1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)

        (application as MainApplication).notesComponent.inject(this)

        viewModel = ViewModelProvider(
            this, factory
        )[NotesViewModel::class.java]

        binding.tool.setNavigationOnClickListener { v ->
            finish()
        }

        if (noteId != -1) {
            viewModel.getNotesById(noteId)
        }
        viewModel.notes.observe(this, Observer {
            binding.notes = it
        })

        binding.saveBtn.setOnClickListener {
            val dateTime = getCurrentDateTime()
            viewModel.insertNotes(
                Notes(
                    noteId.takeIf { it != -1 } ?: 0,
                    binding.edTitle.text.toString(),
                    binding.edDescription.text.toString(),
                    dateTime
                )
            )
            finish()

        }
    }

}
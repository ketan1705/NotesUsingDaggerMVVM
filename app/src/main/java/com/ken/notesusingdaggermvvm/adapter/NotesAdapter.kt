package com.ken.notesusingdaggermvvm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ken.notesusingdaggermvvm.activity.AddNoteActivity
import com.ken.notesusingdaggermvvm.databinding.NotesItemsLayoutBinding
import com.ken.notesusingdaggermvvm.model.Notes
import com.ken.notesusingdaggermvvm.utils.Constants.getColor

class NotesAdapter(
    private val context: Context,
    private val notesList: List<Notes>,
    private val deleteNote: (Int) -> Unit,
) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NotesViewHolder {
        val binding =
            NotesItemsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notesList[position])
        holder.binding.deleteBtn.setOnClickListener {
            deleteNote(notesList[position].id)
        }
        holder.itemView.setOnClickListener {
            context.startActivity(
                Intent(context, AddNoteActivity::class.java).putExtra(
                    "noteID",
                    notesList[position].id
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    class NotesViewHolder(val binding: NotesItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: Notes) {
            binding.cardView.setCardBackgroundColor(getColor())
            binding.notes = notes
        }
    }
}
package br.com.cleantodo.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cleantodo.R
import br.com.cleantodo.presentation.ListActions
import br.com.core.domain.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteListAdapter(var notes: ArrayList<Note>, val actions: ListActions):
RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
    )
    override fun onBindViewHolder(holder: NoteListAdapter.NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }
    override fun getItemCount()= notes.size

    fun updateNotes(newNotes: List<Note>){
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val title = view.title
        private val content = view.content
        private val date = view.date
        private val edit = view.edit
        private val delete = view.delete
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind(note: Note){
            title.text = note.title
            content.text = note.content

            val sdf = SimpleDateFormat("MM dd, HH:mm:ss")
            val resultDate = Date(note.updateTime)
            date.text = "Alteração em: ${sdf.format(resultDate)}"

            edit.setOnClickListener { actions.onClickEditAdapterItem(note.id) }
            delete.setOnClickListener { actions.onClickDeleteAdapterItem(note) }

        }

    }


}
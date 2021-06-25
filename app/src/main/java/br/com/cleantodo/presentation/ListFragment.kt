package br.com.cleantodo.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cleantodo.R
import br.com.cleantodo.framework.ListViewModel
import br.com.cleantodo.presentation.adapter.NoteListAdapter
import br.com.core.domain.Note
import kotlinx.android.synthetic.main.fragment_list.*
import androidx.recyclerview.widget.RecyclerView





class ListFragment : Fragment(), ListActions {

    private val notesListAdapter = NoteListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }
        initNote.setOnClickListener {
            gotToNoteDetails()
        }

        viewModel = androidx.lifecycle.ViewModelProviders.of(this).get(ListViewModel::class.java)
        observeViewModel()

        addNote.setOnClickListener {
            gotToNoteDetails(0L)
        }

    }

    fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty())
                containerInf.visibility = View.VISIBLE
            else{
                containerInf.visibility = View.GONE;
                image.visibility = View.GONE;
            }

            notesListAdapter.updateNotes(it.sortedBy { it -> it.updateTime })
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun gotToNoteDetails(id: Long = 0L) {
        val action = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(recycler).navigate(action)

    }

    override fun onClickEditAdapterItem(id: kotlin.Long) {
        gotToNoteDetails(id)
    }

    override fun onClickDeleteAdapterItem(note: br.com.core.domain.Note) {
        android.app.AlertDialog.Builder(context)
            .setTitle("Você tem certeza")
            .setMessage("Está ação não tem volta, mandou pra narnia, fica em narnia?")
            .setPositiveButton("OK"){ _, _ ->  viewModel.deleteNote(note)}
            .setNegativeButton("DESISTO"){_, _ -> }
            .create()
            .show()

    }
}
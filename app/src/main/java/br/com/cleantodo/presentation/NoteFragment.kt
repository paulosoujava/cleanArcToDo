package br.com.cleantodo.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import br.com.cleantodo.R
import br.com.cleantodo.framework.NoteViewModel
import br.com.core.domain.Note
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.item_note.*


class NoteFragment : Fragment() {
    private var noteId = 0L
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        arguments?.let{
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }
        if(noteId != 0L){
            viewModel.getNote(noteId)
        }

        save.setOnClickListener {
            if (idTitle.text.toString() != "" || idContent.text.toString() != "") {
                val time: Long = System.currentTimeMillis()
                currentNote.title = idTitle.text.toString()
                currentNote.content = idContent.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            } else
                Navigation.findNavController(it).popBackStack()
        }

        observerViewModel()
    }

    private fun observerViewModel(){
        viewModel.saved.observe(viewLifecycleOwner, {
            if(it){
                Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
                hideKeyboard()
                Navigation.findNavController(titleTodo).popBackStack()
            }else{
                Toast.makeText(context, "Ops falha nossa", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner, {
            it?.let {
                currentNote = it
                idTitle.setText( it.title, TextView.BufferType.EDITABLE)
                idContent.setText( it.content, TextView.BufferType.EDITABLE)
            }
        })
    }
    private fun hideKeyboard(){
        val imm : InputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(titleTodo.windowToken, 0)
    }
}
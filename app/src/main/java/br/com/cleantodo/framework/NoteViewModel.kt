package br.com.cleantodo.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.cleantodo.framework.di.ApplicationModule
import br.com.cleantodo.framework.di.DaggerViewModelComponent
import br.com.core.domain.Note
import br.com.core.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val repository = NoteRepository(RoomDataSource(application))

    @Inject
    lateinit var usecases: UseCases
    init {
        DaggerViewModelComponent.builder()
            .applicationModule(
                ApplicationModule(
                    getApplication()
                )
            )
            .build()
            .inject(this)
    }

    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            usecases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            val note = usecases.getNote(id)
            currentNote.postValue(note)
        }
    }

}
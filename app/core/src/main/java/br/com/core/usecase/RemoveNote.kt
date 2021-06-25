package br.com.core.usecase

import br.com.core.domain.Note
import br.com.core.repository.NoteRepository

class RemoveNote ( private val noteRepository: NoteRepository){
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}
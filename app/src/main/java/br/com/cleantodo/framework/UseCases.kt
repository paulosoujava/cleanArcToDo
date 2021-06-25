package br.com.cleantodo.framework

import br.com.core.usecase.AddNote
import br.com.core.usecase.GetAllNotes
import br.com.core.usecase.GetNote
import br.com.core.usecase.RemoveNote

class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)
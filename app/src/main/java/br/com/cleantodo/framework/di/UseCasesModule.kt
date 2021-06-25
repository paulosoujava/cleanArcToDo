package br.com.cleantodo.framework.di

import br.com.cleantodo.framework.UseCases
import br.com.core.repository.NoteRepository
import br.com.core.usecase.AddNote
import br.com.core.usecase.GetAllNotes
import br.com.core.usecase.GetNote
import br.com.core.usecase.RemoveNote
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUserCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository)
    )
}
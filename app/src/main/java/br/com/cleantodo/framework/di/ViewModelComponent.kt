package br.com.cleantodo.framework.di

import br.com.cleantodo.framework.ListViewModel
import br.com.cleantodo.framework.NoteViewModel
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        RepositoryModule::class,
        UseCasesModule::class
    ]
)
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)

}
package br.com.cleantodo.framework.di

import android.app.Application
import br.com.cleantodo.framework.RoomDataSource
import br.com.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesRepository(app:Application) = NoteRepository(RoomDataSource(app))
}
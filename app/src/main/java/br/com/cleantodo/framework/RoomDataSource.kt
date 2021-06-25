package br.com.cleantodo.framework

import android.content.Context
import br.com.cleantodo.framework.dao.DataBaseService
import br.com.cleantodo.framework.dao.NoteEntity
import br.com.core.domain.Note
import br.com.core.repository.NoteDataSource

class RoomDataSource(context: Context): NoteDataSource {
    private val noteDao = DataBaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id).toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note)= noteDao.removeEntity(NoteEntity.fromNote(note))
}
package br.com.cleantodo.framework.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.core.domain.Note

@Entity(tableName = "note")
class NoteEntity(
     val title: String,
     val content: String,
    @ColumnInfo(name = "creation_date")
    val createTime: Long,
    @ColumnInfo(name = "update_date")
    val updateTime: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
) {
    companion object {
        fun fromNote(note: Note) = NoteEntity(
            note.title,
            note.content,
            note.creationTime,
            note.updateTime,
            note.id
        )
    }

    fun toNote() = Note(title, content, createTime, updateTime, id)
}
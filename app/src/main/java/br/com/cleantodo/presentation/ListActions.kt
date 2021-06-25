package br.com.cleantodo.presentation

import br.com.core.domain.Note


interface ListActions {
    fun onClickEditAdapterItem(id: Long)
    fun onClickDeleteAdapterItem(note: Note)
}
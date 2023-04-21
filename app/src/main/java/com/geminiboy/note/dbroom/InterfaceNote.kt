package com.geminiboy.note.dbroom

import androidx.room.*

@Dao
interface InterfaceNote {
    @Insert
    fun insertNote(note: EntityNote)

    @Query("SELECT * FROM EntityNote ORDER BY title ASC ")
    fun getNoteASC(): List<EntityNote>

    @Query("SELECT * FROM EntityNote ORDER BY title DESC")
    fun getDataNote() : List<EntityNote>

    @Delete
    fun deleteNote(note: EntityNote)

    @Update
    fun updateNOte(note: EntityNote)
}
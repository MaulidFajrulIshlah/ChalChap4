package com.geminiboy.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.geminiboy.note.dbroom.DatabaseNote
import com.geminiboy.note.dbroom.EntityNote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel (app : Application) : AndroidViewModel(app) {

    // Implement the ViewModel
    var mNote : MutableLiveData<List<EntityNote>>

    init {
        mNote = MutableLiveData()
        getNote()
    }

    //LiveData Observer
    fun getNoteObservers(): MutableLiveData<List<EntityNote>> {
        return mNote
    }

    //mengambil data untuk di tampilkan
    fun getNote() {
        GlobalScope.launch {
            val dataDao = DatabaseNote.getInstance(getApplication())!!.noteDao()
            val listNote = dataDao.getDataNote()
            mNote.postValue(listNote)
        }
    }

    fun insert(entityNote: EntityNote){
        val dataDao = DatabaseNote.getInstance(getApplication())?.noteDao()
        dataDao!!.insertNote(entityNote)
        getNote()
    }

    fun delete(entityNote: EntityNote){
        val dataDao = DatabaseNote.getInstance(getApplication())!!.noteDao()
        dataDao?.deleteNote(entityNote)
        getNote()
    }

    fun update(entityNote: EntityNote){
        val dataDao = DatabaseNote.getInstance(getApplication())!!.noteDao()
        dataDao?.updateNOte(entityNote)
        getNote()
    }


}
package com.geminiboy.note.dbroom


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class EntityNote (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content : String
) : Serializable
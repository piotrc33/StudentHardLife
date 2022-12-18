package com.example.studenthardlife

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Problem(
    @PrimaryKey(autoGenerate = true)
    val id : Int, // will be generated automatically but in constructor it's needed to pass 0
    val problemTitle: String,
    val description: String,
    val listName: String
)

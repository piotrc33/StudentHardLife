package com.example.studenthardlife

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "problem_list_table")
data class ProblemList(
    @PrimaryKey(autoGenerate = false)
    val listName : String,
)

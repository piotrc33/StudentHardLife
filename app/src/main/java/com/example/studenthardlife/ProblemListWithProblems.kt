package com.example.studenthardlife

import androidx.room.Embedded
import androidx.room.Relation

data class ProblemListWithProblems(
    @Embedded val problemList : ProblemList,

    @Relation(
        parentColumn = "listName",
        entityColumn = "listName"
    )

    val problems : List<Problem>
)

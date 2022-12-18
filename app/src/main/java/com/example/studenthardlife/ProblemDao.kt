package com.example.studenthardlife

import androidx.room.*

@Dao
interface ProblemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProblem(problem: Problem)

}

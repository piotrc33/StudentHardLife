package com.example.studenthardlife

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProblemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProblem(problem: Problem)

    @Query("SELECT * FROM Problem")
    fun getProblems(): LiveData<List<Problem>>
}

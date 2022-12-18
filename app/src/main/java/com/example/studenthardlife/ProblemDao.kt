package com.example.studenthardlife

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProblemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProblem(problem: Problem)

    @Update
    suspend fun updateProblem(problem: Problem)

    @Delete
    suspend fun deleteProblem(problem: Problem)

    @Query("SELECT * FROM Problem")
    fun getProblems(): LiveData<List<Problem>>
}

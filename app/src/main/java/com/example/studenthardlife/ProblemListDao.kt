package com.example.studenthardlife

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProblemListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProblemList(problemList: ProblemList)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProblem(problem: Problem)

    @Update
    suspend fun updateProblemList(problemList: ProblemList)

    @Query("SELECT * FROM problem_list_table")
    fun getProblemLists(): LiveData<List<ProblemList>>

    @Transaction
    @Query("SELECT * FROM problem_list_table WHERE listName = :listName")
    // suspend?
    fun getProblemListWithProblems(listName: String): LiveData<List<ProblemListWithProblems>>
}

package com.example.studenthardlife

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProblemListsViewModel(application: Application) : AndroidViewModel(application) {
    private val db: ProblemListDatabase
    val getAllProblemLists: LiveData<List<ProblemList>>

    val getAllProblems: LiveData<List<Problem>>

    init {
        db = ProblemListDatabase.getDatabase(application)
        getAllProblemLists = db.problemListDao().getProblemLists()
        getAllProblems = db.problemDao().getProblems()
    }

    fun insertList(problemList: ProblemList) {
        viewModelScope.launch {
            db.problemListDao().insertProblemList(problemList)
        }
    }

    fun updateList(problemList: ProblemList) {
        viewModelScope.launch {
            db.problemListDao().updateProblemList(problemList)
        }
    }

    fun insertProblem(problem: Problem) {
        viewModelScope.launch {
            db.problemDao().insertProblem(problem)
        }
    }

    fun updateProblem(problem: Problem) {
        viewModelScope.launch {
            db.problemDao().updateProblem(problem)
        }
    }


    fun deleteProblem(problem: Problem) {
        viewModelScope.launch {
            db.problemDao().deleteProblem(problem)
        }
    }

}
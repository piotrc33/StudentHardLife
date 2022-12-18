package com.example.studenthardlife

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProblemListsViewModel(application: Application) : AndroidViewModel(application) {
    private val db: ProblemListDatabase
    val getAllProblemLists: LiveData<List<ProblemList>>

    init {
        db = ProblemListDatabase.getDatabase(application)
        getAllProblemLists = db.problemListDao().getProblemLists()
    }

    fun insert(problemList: ProblemList) {
        viewModelScope.launch{
            db.problemListDao().insertProblemList(problemList)
        }
    }

}
package com.example.studenthardlife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
//    private val problemListsViewModel : ProblemListsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val adapter = ProblemListsAdapter()

//        problemListsViewModel.getAllProblemLists.observe(this) { problemLists ->
//            problemLists?.let { adapter.setData(it) }
//        }

//        problemListsViewModel.insert(ProblemList("List2"))
//        problemListsViewModel.insertProblem(Problem(0, "problemos", "aplikacja", "List1"))

//        val db = ProblemListDatabase.getDatabase(applicationContext)
//        ProblemLists.initialize()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() ||  super.onSupportNavigateUp()
    }
}
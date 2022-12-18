package com.example.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// Fragments are files between which jetpack navigation is possible
class ProblemListsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
//    private val problemLists: ArrayList<ProblemList> = ProblemLists.data
    private lateinit var problemListsAdapter: ProblemListsAdapter
    private lateinit var problemListsViewModel: ProblemListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_problem_lists, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // ViewModel
        problemListsViewModel = ViewModelProvider(this).get(ProblemListsViewModel::class.java)
        problemListsViewModel.getAllProblemLists.observe(viewLifecycleOwner, Observer {problemList ->
            problemListsAdapter.setData(problemList)
        })

        problemListsAdapter = ProblemListsAdapter()
        recyclerView.adapter = problemListsAdapter
//        println("Siemanko z Fragmentu")

        return view
    }
}
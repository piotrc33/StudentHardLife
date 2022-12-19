package com.example.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studenthardlife.databinding.FragmentProblemListsBinding

// Fragments are files between which jetpack navigation is possible
class ProblemListsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var problemListsAdapter: ProblemListsAdapter
    private lateinit var problemListsViewModel: ProblemListsViewModel

    private lateinit var binding: FragmentProblemListsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProblemListsBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // ViewModel
        problemListsViewModel = ViewModelProvider(this).get(ProblemListsViewModel::class.java)
        problemListsViewModel.getAllProblemLists.observe(viewLifecycleOwner, Observer {problemList ->
            problemListsAdapter.setData(problemList)
        })

        problemListsAdapter = ProblemListsAdapter()
        recyclerView.adapter = problemListsAdapter

        binding.fabAddList.setOnClickListener {
            findNavController().navigate(ProblemListsFragmentDirections.actionProblemListsFragmentToAddListFragment())
        }

        return binding.root
    }

}
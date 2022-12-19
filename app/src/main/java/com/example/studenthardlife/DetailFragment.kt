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
import com.example.studenthardlife.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var detailAdapter: DetailAdapter
    private lateinit var problemListsViewModel: ProblemListsViewModel
    private lateinit var parentListName : String

    private lateinit var binding : FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { parentListName = it.getString("listName").toString() }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // If change happens to database then update local copy
        problemListsViewModel = ViewModelProvider(this).get(ProblemListsViewModel::class.java)
        problemListsViewModel.getAllProblems.observe(viewLifecycleOwner, Observer {problems ->
            detailAdapter.setData(problems)
        })

        detailAdapter = DetailAdapter(parentListName)
        recyclerView.adapter = detailAdapter

        val addButton = binding.fabAdd
        addButton.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToAddProblemFragment(
                listName = parentListName
            ))
        }

        val cameraButton = binding.fabCamera
        cameraButton.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToAddPhotoFragment(
                listName = parentListName
            ))
        }

        return binding.root
    }
}
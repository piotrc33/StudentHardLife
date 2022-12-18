package com.example.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var detailAdapter: DetailAdapter
    private lateinit var problemListsViewModel: ProblemListsViewModel
    private lateinit var parentListName : String
    private lateinit var addButton : FloatingActionButton
    private lateinit var cameraButton : FloatingActionButton
    private lateinit var editButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { parentListName = it.getString("listName").toString() }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // If change happens to database then update local copy
        problemListsViewModel = ViewModelProvider(this).get(ProblemListsViewModel::class.java)
        problemListsViewModel.getAllProblems.observe(viewLifecycleOwner, Observer {problems ->
            detailAdapter.setData(problems)
        })

        detailAdapter = DetailAdapter(parentListName)
        recyclerView.adapter = detailAdapter

        addButton = view.findViewById(R.id.fab_add)
        addButton.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToAddProblemFragment(
                listName = parentListName
            ))
        }

        cameraButton = view.findViewById(R.id.fab_camera)
        cameraButton.setOnClickListener {
//            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToAddProblemFragment(
//                listName = parentListName
//            ))
        }

        return view
    }
}
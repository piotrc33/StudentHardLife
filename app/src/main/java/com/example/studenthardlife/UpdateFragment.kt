package com.example.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

class UpdateFragment : Fragment() {
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var editButton: Button
    private lateinit var deleteButton: Button

    private lateinit var problemId : Number
    private lateinit var problemTitle : String
    private lateinit var problemDescription : String
    private lateinit var parentList : String
    private lateinit var updatedProblem : Problem

    private val problemListsViewModel: ProblemListsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            problemId = it.getInt("id")
            problemTitle = it.getString("problemTitle").toString()
            problemDescription = it.getString("problemDescription").toString()
            parentList = it.getString("parentList").toString()
        }

//         problem = ProblemLists.data[listPosition.toInt()].problems[problemPosition.toInt()]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        // Setting inputs to old values
        titleEditText = view.findViewById(R.id.edit_problem_title)
        titleEditText.setText(problemTitle)

        descriptionEditText= view.findViewById(R.id.edit_problem_description)
        descriptionEditText.setText(problemDescription)

        editButton = view.findViewById(R.id.edit_button)
        editButton.setOnClickListener {
            updateProblem()
        }

        deleteButton = view.findViewById(R.id.delete_button)
        deleteButton.setOnClickListener {
            deleteProblem()
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun updateProblem() {
        val newTitle: String = titleEditText.text.toString()
        val newDescription: String = descriptionEditText.text.toString()

        problemListsViewModel.updateProblem(Problem(problemId as Int, newTitle, newDescription, parentList))
        findNavController().navigate(
            UpdateFragmentDirections.actionUpdateFragmentToDetailFragment(
                listName = parentList
            )
        )
    }

    private fun deleteProblem() {
        problemListsViewModel.deleteProblem(Problem(problemId as Int, problemTitle, problemDescription, parentList))

        findNavController().navigate(
            UpdateFragmentDirections.actionUpdateFragmentToDetailFragment(
                listName = parentList
            )
        )
    }

}
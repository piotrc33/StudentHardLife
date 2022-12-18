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

class AddProblemFragment : Fragment() {
    private var listName: String? = null
    private lateinit var newProblemTitleEditText: EditText
    private lateinit var newProblemDescriptionEditText: EditText
    private lateinit var submitButton: Button
    private val problemListsViewModel: ProblemListsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            listName = it.getString("listName")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_problem, container, false)
        newProblemTitleEditText = view.findViewById(R.id.new_problem_title)
        newProblemDescriptionEditText = view.findViewById(R.id.new_problem_description)
        submitButton = view.findViewById(R.id.submit_button)

        submitButton.setOnClickListener {
            submitProblem()
        }

        return view
    }

    private fun submitProblem() {
        val title: String = newProblemTitleEditText.text.toString()
        val description: String = newProblemDescriptionEditText.text.toString()

        problemListsViewModel.insertProblem(Problem(0, title, description, listName.toString()))
        findNavController().navigate(
            AddProblemFragmentDirections.actionAddProblemFragmentToDetailFragment(
                listName = listName.toString()
            )
        )
    }

}
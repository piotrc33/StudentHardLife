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
import com.example.studenthardlife.databinding.FragmentDetailBinding
import com.example.studenthardlife.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText

    private lateinit var problemId : Number
    private lateinit var problemTitle : String
    private lateinit var problemDescription : String
    private lateinit var parentList : String

    private val problemListsViewModel: ProblemListsViewModel by viewModels()

    private lateinit var binding : FragmentUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            problemId = it.getInt("id")
            problemTitle = it.getString("problemTitle").toString()
            problemDescription = it.getString("problemDescription").toString()
            parentList = it.getString("parentList").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        // Setting inputs to old values
        titleEditText = binding.editProblemTitle
        titleEditText.setText(problemTitle)

        descriptionEditText= binding.editProblemDescription
        descriptionEditText.setText(problemDescription)

        // Setting click listeners
        binding.editButton.setOnClickListener {
            updateProblem()
        }

        binding.deleteButton.setOnClickListener {
            deleteProblem()
        }

        return binding.root
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
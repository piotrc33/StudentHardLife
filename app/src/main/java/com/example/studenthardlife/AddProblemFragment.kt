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
import com.example.studenthardlife.databinding.FragmentAddProblemBinding
import com.example.studenthardlife.databinding.FragmentUpdateBinding

class AddProblemFragment : Fragment() {
    private var listName: String? = null
    private lateinit var newProblemTitleEditText: EditText
    private lateinit var newProblemDescriptionEditText: EditText

    private val problemListsViewModel: ProblemListsViewModel by viewModels()

    private lateinit var binding: FragmentAddProblemBinding

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
        binding = FragmentAddProblemBinding.inflate(inflater, container, false)
        newProblemTitleEditText = binding.newProblemTitle
        newProblemDescriptionEditText = binding.newProblemDescription
        binding.submitButton.setOnClickListener {
            submitProblem()
        }

        return binding.root
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
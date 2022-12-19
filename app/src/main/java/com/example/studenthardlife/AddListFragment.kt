package com.example.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.studenthardlife.databinding.FragmentAddListBinding
import com.example.studenthardlife.databinding.FragmentAddProblemBinding

class AddListFragment : Fragment() {
    private lateinit var listNameEditText: EditText
    private lateinit var binding: FragmentAddListBinding

    private val problemListsViewModel: ProblemListsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddListBinding.inflate(inflater, container, false)

        listNameEditText = binding.listName

        binding.addButton.setOnClickListener {
            addList()
        }

        return binding.root
    }

    private fun addList() {
        val listName: String = listNameEditText.text.toString()

        problemListsViewModel.insertList(ProblemList(listName, ""))
        findNavController().navigate(
            AddListFragmentDirections.actionAddListFragmentToProblemListsFragment()
        )
    }
}
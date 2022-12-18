package com.example.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

class UpdateFragment : Fragment() {
    private lateinit var problemPosition : Number
    private lateinit var listPosition : Number
    private lateinit var problem : Problem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            problemPosition = it.getInt("problemPosition")
            listPosition = it.getInt("listPosition") }
//         problem = ProblemLists.data[listPosition.toInt()].problems[problemPosition.toInt()]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_update, container, false)
        val titleEditText: EditText = view.findViewById(R.id.edit_problem_title)
        val descriptionEditText: EditText = view.findViewById(R.id.edit_problem_description)
//        titleEditText.setText(problem.title)
//        descriptionEditText.setText(problem.description)
        // Inflate the layout for this fragment
        return view
    }

}
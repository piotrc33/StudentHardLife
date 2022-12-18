package com.example.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var detailAdapter: DetailAdapter
//    private lateinit var listPosition: Number
//    private lateinit var list: ProblemList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        arguments?.let { listPosition = it.getInt("listPosition") }
//        list = ProblemLists.data[listPosition.toInt()]
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

        detailAdapter = DetailAdapter()
        recyclerView.adapter = detailAdapter

        return view
    }
}
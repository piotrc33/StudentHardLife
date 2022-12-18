package com.example.studenthardlife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

// Adapter is a file that manages data displayed onto recycler view, creating and binding view holders
class DetailAdapter(private val parentListName: String) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.problem_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.problem_description)
        val editButton: ImageView = itemView.findViewById(R.id.edit_icon)
        val problemCardView: CardView = itemView.findViewById(R.id.problem_card_view)
    }

    // list of all problems from all lists
    private var problems: List<Problem> = listOf()

    fun setData(newProblems: List<Problem>) {
        this.problems = newProblems.filter { it.listName == parentListName }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.problem_item, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
//        val problemsFiltered = problems.filter { it.listName == parentListName }
        var problem : Problem
        if (problems.isNotEmpty()) {
            problem = problems[position]
            holder.titleTextView.text = problem.problemTitle
            holder.descriptionTextView.text = problem.description

            holder.editButton.setOnClickListener {
                holder.problemCardView.findNavController()
                    .navigate(DetailFragmentDirections.actionDetailFragmentToUpdateFragment(
                        id = problem.id,
                        problemTitle = problem.problemTitle,
                        problemDescription = problem.description,
                        parentList = parentListName
                    ))
            }
        }


//        editButton = view.findViewById(R.id.edit_icon)
//        editButton.setOnClickListener {
////            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToUpdateFragment()
//        }

//        holder.

//        holder.problemCardView.setOnClickListener {
//            val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment(
//                problemPosition = position, // passing argument to next fragment
//                listPosition = listPosition.toInt()
//            )
//
////             navigating to next fragment
//            holder.problemCardView.findNavController()
//                .navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return problems.size
    }
}